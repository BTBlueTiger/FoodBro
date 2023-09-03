package com.bluetiger.foodbrocompose.main_activity

import android.content.Context
import androidx.navigation.NavController
import com.bluetiger.foodbrocompose.permission.PermissionState

sealed class FoodBroActivityEvent {

    data class PermissionRequest(val permission: String, val result : PermissionState, val context: Context) :
        FoodBroActivityEvent()

    data class Logout(val navController: NavController) : FoodBroActivityEvent()
}
