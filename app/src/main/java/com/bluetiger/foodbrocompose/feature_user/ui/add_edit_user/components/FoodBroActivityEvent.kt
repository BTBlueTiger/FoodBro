package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components

import com.bluetiger.foodbrocompose.main_activity.NavRoutes

sealed class FoodBroActivityEvent{
    data class NavRouteChanged(val navRoutes: NavRoutes) : FoodBroActivityEvent()
}
