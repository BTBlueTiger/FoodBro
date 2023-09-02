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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bluetiger.foodbrocompose.main_activity.NavRoutes
import com.bluetiger.foodbrocompose.main_activity.RouteType
import kotlinx.coroutines.launch

@Composable
fun FoodBroNavigationDrawer(
    drawerState: DrawerState,
    selectedNavRoute: NavRoutes,
    navRoutes: List<NavRoutes>,
    onClick: (NavRoutes) -> Unit,
    navHost: @Composable () -> Unit
) {

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.fillMaxWidth(0.7f)) {
                navRoutes
                    .filter { it.routeType != RouteType.INTERN }
                    .groupBy { it.category }
                    .map { navRouteList ->
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = navRouteList.key.displayString,
                            modifier = Modifier.padding(4.dp)
                        )
                        navRouteList.value.map { item ->
                            if (item.routeType != RouteType.INTERN)
                                NavigationDrawerItem(
                                    icon = { Icon(item.icon, contentDescription = "") },
                                    label = { Text(item.screenName) },
                                    selected = item == selectedNavRoute,
                                    onClick = {
                                        onClick(item)
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