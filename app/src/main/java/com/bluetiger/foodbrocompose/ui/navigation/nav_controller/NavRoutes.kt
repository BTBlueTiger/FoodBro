package com.bluetiger.foodbrocompose.ui.navigation.nav_controller

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

enum class NavRoutes(
    val icon: ImageVector,
    val screenName: String,
    val navOnClick: (NavController) -> Unit
) {

    HOME(
        Icons.TwoTone.Home,
        "HomeScreen",
        navOnClick = { navController -> navController.navigate(HOME.screenName) }),
    NEW_USER(
        Icons.TwoTone.Face,
        "New User",
        navOnClick = { navController -> navController.navigate(NEW_USER.screenName) }),
    FOOD_LIST(
        Icons.TwoTone.List,
        "Food Table",
        navOnClick = { navController -> navController.navigate(FOOD_LIST.screenName) }),
    FOOD_PREDICTION(
        Icons.TwoTone.Info,
        "Food Prediction",
        navOnClick = { navController -> navController.navigate(FOOD_PREDICTION.screenName) }),
}