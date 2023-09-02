package com.bluetiger.foodbrocompose.main_activity

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.FoodFactsByBarcodeScreen
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.AddEditUserScreen
import com.bluetiger.foodbrocompose.feature_user.ui.user_list.UserListScreen

enum class NavRouteCategory(val displayString: String) {
    COMMON("Common"), USER("User"), FOOD("Food"), INTERN("Intern")
}

enum class RouteType {
    FREE_ACCESS, USER_DEPENDING, INTERN
}

enum class NavRoutes(
    val iconID: Int,
    val screenName: String,
    val routeType: RouteType,
    val category: NavRouteCategory,
    val navOnClick: (NavController) -> Unit,
    val targets: List<String> = emptyList()
) {

    HOME(
        R.drawable.twotone_home_24,
        "HomeScreen",
        RouteType.USER_DEPENDING,
        NavRouteCategory.COMMON,
        navOnClick = { navController -> navController.navigate(HOME) }),
    USER_LIST(
        R.drawable.twotone_people_24,
        "User List",
        RouteType.FREE_ACCESS,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(USER_LIST) }),
    USER(
        R.drawable.twotone_person_24,
        "You",
        RouteType.USER_DEPENDING,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(USER) }
    ),
    PHYSICAL_ACTIVITY(
        R.drawable.twotone_sports_24,
        "Activity",
        RouteType.USER_DEPENDING,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(PHYSICAL_ACTIVITY) }
    ),
    NEW_USER(
        R.drawable.twotone_person_add_24,
        "New User",
        RouteType.FREE_ACCESS,
        NavRouteCategory.USER,
        navOnClick = { navController -> navController.navigate(NEW_USER) }),
    FOOD_FACTS_BY_BARCODE(
        R.drawable.twotone_qr_code_scanner_24,
        "Food Facts by Barcode",
        RouteType.FREE_ACCESS,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(FOOD_FACTS_BY_BARCODE) }
    ),
    FOOD_LIST(
        R.drawable.twotone_table_chart_24,
        "Food Table",
        RouteType.FREE_ACCESS,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(FOOD_LIST) }),
    FOOD_PREDICTION(
        R.drawable.twotone_question_mark_24,
        "Food Prediction",
        RouteType.FREE_ACCESS,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(FOOD_PREDICTION) }),
    OPEN_FOOD_FACT_SCREEN(
        R.drawable.twotone_food_bank_24,
        "OpenFoodFactScreens",
        RouteType.INTERN,
        NavRouteCategory.INTERN,
        navOnClick = { navController -> navController.navigate(OPEN_FOOD_FACT_SCREEN) },
        listOf("barcode")),
    SCANS(
        R.drawable.twotone_history_24,
        "Last_Scans",
        RouteType.USER_DEPENDING,
        NavRouteCategory.FOOD,
        navOnClick = { navController -> navController.navigate(SCANS) }
    )
    ;

    @Composable
    fun GetComposable(route: NavRoutes, navController: NavController) = when (route) {
        HOME -> Text(text = "Hello")
        USER -> Text(text = "Hello USER")
        USER_LIST -> UserListScreen()
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
