package com.bluetiger.foodbrocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bluetiger.foodbrocompose.ui.navigation.nav_controller.NavRoutes
import com.bluetiger.foodbrocompose.ui.navigation.nav_controller.foodBroNavigator
import com.bluetiger.foodbrocompose.ui.navigation.nav_drawer.FoodBroNavigationDrawer
import com.bluetiger.foodbrocompose.ui.screens.home.Home
import com.bluetiger.foodbrocompose.ui.screens.new_user.NewUserUser

import com.bluetiger.foodbrocompose.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Graph.provide(this)
        setContent {
            val navController = foodBroNavigator()

            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FoodBroNavigationDrawer(
                        navController = navController,
                        content = {
                            NavHost(navController = navController, startDestination = NavRoutes.HOME.screenName) {
                                composable(NavRoutes.HOME.screenName) {
                                    Home()
                                }
                                composable(NavRoutes.NEW_USER.screenName) {
                                    NewUserUser()
                                }
                                composable(NavRoutes.FOOD_LIST.screenName) {
                                    Text(text = "Hello Food List!")
                                }
                                composable(NavRoutes.FOOD_PREDICTION.screenName) {
                                    Text(text = "Hello Food Prediction!")
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}