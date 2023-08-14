package com.bluetiger.foodbrocompose.main_activity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.List
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.AddEditUserScreen
import com.bluetiger.old.screens.home.HomeScreen

enum class NavRouteCategory(val displayString: String){
    COMMON("Common"), USER("User"), FOOD("Food")
}

enum class NavRoutes(
    val icon: ImageVector,
    val screenName: String,
    val userDependingRoute : Boolean,
    val category: NavRouteCategory,
    val navOnClick: (NavController) -> Unit,
) {

    HOME(
        Icons.TwoTone.Home,
        "HomeScreen",
        true,
        NavRouteCategory.COMMON,
        navOnClick = { navController -> navController.navigate(HOME.screenName) }),
    USER_LIST(
        Icons.TwoTone.Person,
        "User List",
        false,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(USER_LIST.screenName) }),
    USER(
        Icons.TwoTone.Person,
        "You",
        true,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(USER.screenName) }
    ),
    PHYSICAL_ACTIVITY(
        Icons.TwoTone.Star,
        "Activity",
        true,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(PHYSICAL_ACTIVITY.screenName) }
    ),
    NEW_USER(
        Icons.TwoTone.Face,
        "New User",
        false,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(NEW_USER.screenName) }),
    FOOD_LIST(
        Icons.TwoTone.List,
        "Food Table",
        false,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(FOOD_LIST.screenName) }),
    FOOD_PREDICTION(
        Icons.TwoTone.Info,
        "Food Prediction",
        false,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(FOOD_PREDICTION.screenName) });

    @Composable
    fun GetComposable(route: NavRoutes, navController: NavController) = when(route){
        HOME -> HomeScreen()
        USER -> Text(text = "Hello USER")
        USER_LIST -> Text(text = "Hello UserList")
        PHYSICAL_ACTIVITY -> Text(text = "Hello PHYSICAL_ACTIVITY")
        NEW_USER -> AddEditUserScreen(navigateToHome = { navController.navigate(HOME.screenName) })
        FOOD_LIST -> Text(text = "Hello Food List")
        FOOD_PREDICTION -> Text(text = "Hello Food Prediction")
    }
}