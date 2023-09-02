package com.bluetiger.foodbrocompose.main_activity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.FoodFactsByBarcodeScreen
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.AddEditUserScreen

enum class NavRouteCategory(val displayString: String) {
    COMMON("Common"), USER("User"), FOOD("Food"), INTERN("Intern")
}

enum class RouteType {
    FREE_ACCESS, USER_DEPENDING, INTERN
}

enum class NavRoutes(
    val icon: ImageVector,
    val screenName: String,
    val routeType: RouteType,
    val category: NavRouteCategory,
    val navOnClick: (NavController) -> Unit,
    val targets: List<String> = emptyList()
) {

    HOME(
        Icons.TwoTone.Home,
        "HomeScreen",
        RouteType.USER_DEPENDING,
        NavRouteCategory.COMMON,
        navOnClick = { navController -> navController.navigate(HOME) }),
    USER_LIST(
        Icons.TwoTone.Person,
        "User List",
        RouteType.FREE_ACCESS,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(USER_LIST) }),
    USER(
        Icons.TwoTone.Person,
        "You",
        RouteType.USER_DEPENDING,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(USER) }
    ),
    PHYSICAL_ACTIVITY(
        Icons.TwoTone.Star,
        "Activity",
        RouteType.USER_DEPENDING,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(PHYSICAL_ACTIVITY) }
    ),
    NEW_USER(
        Icons.TwoTone.Face,
        "New User",
        RouteType.FREE_ACCESS,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(NEW_USER) }),
    FOOD_FACTS_BY_BARCODE(
        Icons.TwoTone.Info,
        "Food Facts by Barcode",
        RouteType.FREE_ACCESS,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(FOOD_FACTS_BY_BARCODE) },
        listOf("Food Facts by Barcode/{barcode}")
    ),
    FOOD_LIST(
        Icons.TwoTone.List,
        "Food Table",
        RouteType.FREE_ACCESS,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(FOOD_LIST) }),
    FOOD_PREDICTION(
        Icons.TwoTone.Info,
        "Food Prediction",
        RouteType.FREE_ACCESS,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(FOOD_PREDICTION) }),
    OPEN_FOOD_FACT_SCREEN(
        Icons.Default.AddCircle,
        "OpenFoodFactScreens",

        RouteType.INTERN,
        NavRouteCategory.INTERN,
        navOnClick = {
                navController -> navController.navigate(OPEN_FOOD_FACT_SCREEN)
        },
        listOf("barcode")
    );

    @Composable
    fun GetComposable(route: NavRoutes, navController: NavController) = when (route) {
        USER -> Text(text = "Hello USER")
        USER_LIST -> Text(text = "Hello UserList")
        PHYSICAL_ACTIVITY -> Text(text = "Hello PHYSICAL_ACTIVITY")
        NEW_USER -> AddEditUserScreen(navigateToHome = { navController.navigate(HOME.screenName) })
        FOOD_FACTS_BY_BARCODE -> FoodFactsByBarcodeScreen {
            navController.navigate(
                OPEN_FOOD_FACT_SCREEN.screenName + "/$it",
            )
        }

        FOOD_LIST -> Text(text = "Hello Food List")
        FOOD_PREDICTION -> Text(text = "Hello Food Prediction")
        else -> {}
    }

    @Composable
    fun GetComposableWithArguments(){

    }

}

private fun NavController.navigate(
    route: NavRoutes,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    this.navigate(route.screenName, navOptions, navigatorExtras)
}
