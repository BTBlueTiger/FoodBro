package com.bluetiger.foodbrocompose.main_activity

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.FoodFactScreen
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.FoodBroActivityEvent
import com.bluetiger.foodbrocompose.main_activity.components.FoodBroNavigationDrawer
import com.bluetiger.foodbrocompose.permission.PermissionState
import com.bluetiger.foodbrocompose.permission.repository.PermissionRepositoryImpl
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
        Log.e("RequestCode", requestCode.toString())

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
        val selectedNavRouteState = viewModel.selectedNavRoute.value
        val selectAbleNavRoutesSnapshot = viewModel.selectAbleNavRoutes.toList()

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()


        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "FoodBro",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.TwoTone.Menu, contentDescription = "")
                        }
                    },
                    actions = {

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
                    selectedNavRoute = selectedNavRouteState,
                    navRoutes = selectAbleNavRoutesSnapshot,
                    onClick = {
                        navController.navigate(it.screenName)
                        viewModel.onEvent(FoodBroActivityEvent.NavRouteChanged(it))
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = viewModel.startDestination.screenName
                    ) {
                        selectAbleNavRoutesSnapshot.forEach { route ->
                            composable(route.screenName) {
                                route.GetComposable(route = route, navController = navController)
                            }
                            route.targets.forEach {
                                composable(route.screenName + "/{$it}") {it2 ->
                                    FoodFactScreen(it2.arguments?.getString(it)!!)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

