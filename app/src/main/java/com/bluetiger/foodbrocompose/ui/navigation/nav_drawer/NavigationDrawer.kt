package com.bluetiger.foodbrocompose.ui.navigation.nav_drawer

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bluetiger.foodbrocompose.Graph
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.ui.navigation.nav_controller.NavRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserEmail(){
    var emailState by remember { mutableStateOf("") }

    LaunchedEffect(key1 = emailState){
        Graph.setGlobalUserByEmail(emailState)
    }

    if(FBPreferences.getInstance().isUserSet()){
        val email = FBPreferences.getInstance().getUserEmail()?: ""
        if(email.isNotEmpty()){
            emailState = email
        }
    }
    val user by Graph.user.collectAsState()
    if (user.email.isNotEmpty()) {
        Text(text = "Hello ${user.email}")
    }
}

@Composable
fun FoodBroNavigationDrawer(
    navController: NavController,
    width: Float = 0.75f,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val screenWidth = LocalConfiguration.current.screenWidthDp * width

    val items = NavRoutes.values()
    val selectedItem = remember { mutableStateOf(items[0]) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                Text(text = "First Components", modifier = Modifier.padding(4.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = "") },
                        label = { Text(item.screenName) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                            item.navOnClick.invoke(navController)
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                            .width(screenWidth.dp)
                    )
                }
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .width(screenWidth.dp)
                )
            }
        }, content = {
            Column(Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { scope.launch { drawerState.open() } },
                        content = { Icon(Icons.TwoTone.Menu, contentDescription = "") }
                    )
                    UserEmail()
                }
                content()
            }

        }
    )
}