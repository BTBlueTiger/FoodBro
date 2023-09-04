package com.bluetiger.foodbrocompose.main_activity

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.FoodFactsByBarcodeScreen
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.OpenFoodFactScreen
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.AddEditUserScreen
import com.bluetiger.foodbrocompose.feature_user.ui.user_list.UserListScreen


sealed class NavManager {
    enum class NavRouteCategory(val displayString: String) {
        COMMON("Common"), USER("User"), FOOD("Food"), INTERN("Intern")
    }

    enum class RouteType {
        FREE_ACCESS, USER_DEPENDING, INTERN
    }

    abstract val iconID: Int
    abstract val screenName: String
    abstract val routeType: RouteType
    abstract val category: NavRouteCategory
    abstract val extras: List<String>

    abstract fun addRoute(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        topModel: FoodBroActivityModel
    )

    data class Home(
        override val iconID: Int = R.drawable.twotone_home_24,
        override val screenName: String = "Home",
        override val routeType: RouteType = RouteType.FREE_ACCESS,
        override val category: NavRouteCategory = NavRouteCategory.COMMON,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                Text(text = "UserList")
            }
        }
    }

    data class UserList(
        override val iconID: Int = R.drawable.twotone_people_24,
        override val screenName: String = "User List",
        override val routeType: RouteType = RouteType.FREE_ACCESS,
        override val category: NavRouteCategory = NavRouteCategory.USER,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                UserListScreen(navigateToHome = {
                    navController.navigate(Home().screenName)
                }, topModel)
            }
        }
    }

    data class User(
        override val iconID: Int = R.drawable.twotone_person_24,
        override val screenName: String = "You",
        override val routeType: RouteType = RouteType.USER_DEPENDING,
        override val category: NavRouteCategory = NavRouteCategory.USER,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                Text(text = "UserList")
            }
        }
    }

    data class PhysicalActivity(
        override val iconID: Int = R.drawable.twotone_sports_24,
        override val screenName: String = "Activity",
        override val routeType: RouteType = RouteType.USER_DEPENDING,
        override val category: NavRouteCategory = NavRouteCategory.USER,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                Text(text = "UserList")
            }
        }
    }

    data class NewUser(
        override val iconID: Int = R.drawable.twotone_person_add_24,
        override val screenName: String = "New User",
        override val routeType: RouteType = RouteType.FREE_ACCESS,
        override val category: NavRouteCategory = NavRouteCategory.USER,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                AddEditUserScreen(navigateToHome = {
                    navController.navigate(Home().screenName)
                })
            }
        }
    }

    data class EditUser(
        override val iconID: Int = R.drawable.twotone_person_pin_24,
        override val screenName: String = "Edit User",
        override val routeType: RouteType = RouteType.USER_DEPENDING,
        override val category: NavRouteCategory = NavRouteCategory.USER,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                AddEditUserScreen(navigateToHome = {
                    navController.navigate(Home().screenName)
                })
            }
        }
    }

    data class FoodFactsByBarcode(
        override val iconID: Int = R.drawable.twotone_qr_code_scanner_24,
        override val screenName: String = "Food Facts by Barcode",
        override val routeType: RouteType = RouteType.FREE_ACCESS,
        override val category: NavRouteCategory = NavRouteCategory.FOOD,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                FoodFactsByBarcodeScreen(navigateToOpenFoodFacts = { navController.navigate(OpenFoodFact().screenName + "/$it") })
            }
        }
    }

    data class OpenFoodFact(
        override val iconID: Int = R.drawable.twotone_food_bank_24,
        override val screenName: String = "OpenFoodFactScreens",
        override val routeType: RouteType = RouteType.INTERN,
        override val category: NavRouteCategory = NavRouteCategory.INTERN,
        override val extras: List<String> = listOf("barcode")
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable("$screenName/{barcode}") {
                OpenFoodFactScreen()
            }
        }
    }

    data class FoodList(
        override val iconID: Int = R.drawable.twotone_table_chart_24,
        override val screenName: String = "Food Table",
        override val routeType: RouteType = RouteType.FREE_ACCESS,
        override val category: NavRouteCategory = NavRouteCategory.FOOD,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                Text(text = "UserList")
            }
        }
    }

    data class FoodPrediction(
        override val iconID: Int = R.drawable.twotone_question_mark_24,
        override val screenName: String = "Food Prediction",
        override val routeType: RouteType = RouteType.FREE_ACCESS,
        override val category: NavRouteCategory = NavRouteCategory.FOOD,
        override val extras: List<String> = emptyList()
    ) : NavManager() {
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                Text(text = "UserList")
            }
        }
    }

    class Scans : NavManager() {

        override val iconID: Int = R.drawable.twotone_history_24
        override val screenName: String = "Last_Scans"
        override val routeType: RouteType = RouteType.USER_DEPENDING
        override val category: NavRouteCategory = NavRouteCategory.FOOD
        override val extras: List<String> = emptyList()
        override fun addRoute(
            navGraphBuilder: NavGraphBuilder,
            navController: NavController,
            topModel: FoodBroActivityModel
        ) {
            navGraphBuilder.composable(screenName) {
                Text(text = "UserList")
            }
        }
    }

    companion object {
        private val home = Home()
        private val userList = UserList()
        private val physicalActivity = PhysicalActivity()
        private val newUser = NewUser()
        private val editUser = EditUser()
        private val foodFactsByBarcode = FoodFactsByBarcode()
        private val foodList = FoodList()
        private val foodPrediction = FoodPrediction()
        private val openFoodFactScreen = OpenFoodFact()
        private val scans = Scans()


        val navRoutes = listOf(
            home,
            userList,
            physicalActivity,
            newUser,
            editUser,
            foodFactsByBarcode,
            foodList,
            foodPrediction,
            openFoodFactScreen,
            scans
        )

        val navRoutesUserDepending = listOf(
            physicalActivity,
            editUser,
            scans,
        )

        val navRoutesFreeAccess = listOf(
            home,
            userList,
            newUser,
            foodFactsByBarcode,
            foodList,
            foodPrediction,
        )
    }


}
