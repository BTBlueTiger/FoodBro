package com.bluetiger.foodbrocompose.main_activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.main_activity.components.FoodBroNavigationDrawer
import com.bluetiger.foodbrocompose.permission.PermissionState
import com.bluetiger.foodbrocompose.permission.repository.PermissionRepositoryImpl
import com.bluetiger.foodbrocompose.ui.common.components.headline.HeadLine
import com.bluetiger.foodbrocompose.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FoodBroActivity() : AppCompatActivity() {

    private var foodBroModel: FoodBroActivityModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FBPreferences.getInstance().initiate("FoodBro", this)
        setContent {
            AppTheme {
                //val viewModel = hiltViewModel<OpenApiFactsViewModel>()
                FoodBro()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (grantResults[0]) {
            PackageManager.PERMISSION_GRANTED -> {
                PermissionState.GRANTED
            }

            PackageManager.PERMISSION_DENIED -> {
                if (this.shouldShowRequestPermissionRationale(permissions[0])) {
                    PermissionState.RATIONAL
                } else {
                    PermissionState.DENIED
                }
            }

            else -> {
                throw PermissionRepositoryImpl.InvalidPermissionRequest(
                    "Something went wrong in onRequestPermissionsResult"
                )
            }
        }.also {
            foodBroModel?.onEvent(
                FoodBroActivityEvent.PermissionRequest(
                    permissions[0], it, this
                )
            )
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun FoodBro(
        viewModel: FoodBroActivityModel = hiltViewModel()
    ) {
        foodBroModel = viewModel

        val userState = viewModel.user.collectAsState()

        val topBarHeadline = remember { mutableStateOf("FoodBro") }

        LaunchedEffect(key1 = userState.value){
          topBarHeadline.value = userState.value?.email ?: "FoodBro"
        }

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()

        Log.e("User", userState.toString())

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        HeadLine(headline = topBarHeadline.value)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.currentValue == DrawerValue.Closed)
                                    drawerState.open()
                                else
                                    drawerState.close()
                            }
                        }) {
                            Icon(Icons.TwoTone.Menu, contentDescription = "")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            viewModel.onEvent(FoodBroActivityEvent.Logout(navController))
                            Log.e("Viemodel", viewModel.user.toString())
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.twotone_logout_24), ""
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                FoodBroNavigationDrawer(
                    drawerState = drawerState,
                    //selectedNavRoute = NavManager.UserList(),
                    viewModel = viewModel,
                    onClick = {
                        navController.navigate(it.screenName)

                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavManager.UserList().screenName
                    ) {
                        NavManager.navRoutes.forEach {
                            it.addRoute(this, navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}

