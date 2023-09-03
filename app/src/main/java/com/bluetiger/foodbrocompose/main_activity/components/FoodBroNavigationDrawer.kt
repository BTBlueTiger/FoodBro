package com.bluetiger.foodbrocompose.main_activity.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.main_activity.FoodBroActivityModel
import com.bluetiger.foodbrocompose.main_activity.NavManager
import kotlinx.coroutines.launch


@Composable
fun FoodBroNavigationDrawer(
    drawerState: DrawerState,
    selectedNavRoute: NavManager? = null,
    onClick: (NavManager) -> Unit,
    viewModel: FoodBroActivityModel,
    navHost: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val userState = viewModel.user.collectAsState()
    val routes = remember { mutableStateListOf<NavManager>() }

    LaunchedEffect(key1 = userState.value) {
        routes.clear()
        routes.addAll(
            if (userState.value != null) {
                NavManager.navRoutesFreeAccess + NavManager.navRoutesUserDepending
            } else {
                NavManager.navRoutesFreeAccess
            }
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.fillMaxWidth(0.7f)) {
                routes.groupBy {
                    it.category
                }.map { navRoutes ->
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = navRoutes.key.displayString,
                        modifier = Modifier.padding(4.dp)
                    )
                    navRoutes.value.map {
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painterResource(id = it.iconID),
                                    contentDescription = ""
                                )
                            },
                            label = { Text(it.screenName) },
                            selected = it == selectedNavRoute,
                            onClick = {
                                onClick(it)
                                scope.launch { drawerState.close() }
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .fillMaxWidth()
                        )
                    }
                    Divider()
                }
            }
        }) {
        navHost()
    }
}
