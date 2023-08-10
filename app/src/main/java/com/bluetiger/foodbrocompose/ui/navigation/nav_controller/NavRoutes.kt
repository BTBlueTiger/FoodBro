package com.bluetiger.foodbrocompose.ui.navigation.nav_controller

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.List
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

enum class NavRoutes(
    val icon: ImageVector,
    val screenName: String,
    val userDependingRoute : Boolean,
    val navOnClick: (NavController) -> Unit,
) {

    HOME(
        Icons.TwoTone.Home,
        "HomeScreen",
        false,
        navOnClick = { navController -> navController.navigate(HOME.screenName) }),
    USER_LIST(
        Icons.TwoTone.Person,
        "User List",
        false,
        navOnClick = { navController -> navController.navigate(USER_LIST.screenName) }),
    USER(
        Icons.TwoTone.Person,
        "You",
        true,
        navOnClick = { navController -> navController.navigate(USER.screenName) }
    ),
    PHYSICAL_ACTIVITY(
        Icons.TwoTone.Star,
        "Activity",
        true,
        navOnClick = { navController -> navController.navigate(PHYSICAL_ACTIVITY.screenName) }
    ),
    NEW_USER(
        Icons.TwoTone.Face,
        "New User",
        false,
        navOnClick = { navController -> navController.navigate(NEW_USER.screenName) }),
    FOOD_LIST(
        Icons.TwoTone.List,
        "Food Table",
        false,
        navOnClick = { navController -> navController.navigate(FOOD_LIST.screenName) }),
    FOOD_PREDICTION(
        Icons.TwoTone.Info,
        "Food Prediction",
        false,
        navOnClick = { navController -> navController.navigate(FOOD_PREDICTION.screenName) }),
}