package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components

import android.content.Context
import com.bluetiger.foodbrocompose.main_activity.NavRoutes
import com.bluetiger.foodbrocompose.permission.PermissionState

sealed class FoodBroActivityEvent {
    data class NavRouteChanged(val navRoutes: NavRoutes) : FoodBroActivityEvent()
    data class PermissionRequest(val permission: String, val result : PermissionState, val context: Context) :
        FoodBroActivityEvent()
}
