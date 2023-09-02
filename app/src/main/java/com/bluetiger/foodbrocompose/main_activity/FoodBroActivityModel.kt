package com.bluetiger.foodbrocompose.main_activity

import android.Manifest
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.FoodBroActivityEvent
import com.bluetiger.foodbrocompose.permission.use_case.PermissionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class FoodBroActivityModel @Inject constructor(
    private val permissionUseCases: PermissionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedNavRoute = mutableStateOf(NavRoutes.HOME)
    val selectedNavRoute: State<NavRoutes> = _selectedNavRoute

    private val _selectAbleNavRoutes = mutableStateListOf<NavRoutes>()
    val selectAbleNavRoutes: SnapshotStateList<NavRoutes> = _selectAbleNavRoutes

    lateinit var startDestination: NavRoutes

    init {
        val user = savedStateHandle.get<User>("User")
        startDestination = if (user == null) {
            _selectAbleNavRoutes.addAll(
                NavRoutes.values().filter { it.routeType != RouteType.USER_DEPENDING }
            )
            NavRoutes.USER_LIST
        } else {
            _selectAbleNavRoutes.addAll(
                NavRoutes.values().filter { it.routeType != RouteType.INTERN }
            )
            NavRoutes.HOME
        }
    }

    fun onEvent(event: FoodBroActivityEvent) {
        when (event) {
            is FoodBroActivityEvent.NavRouteChanged -> {
                _selectedNavRoute.value = event.navRoutes
            }

            is FoodBroActivityEvent.PermissionRequest -> {
                viewModelScope.launch {
                    when (event.permission) {
                        Manifest.permission.CAMERA -> {
                            permissionUseCases.camara.setRequestedPermissionResult(
                                event.result
                            )
                        }
                    }
                }
            }
        }
    }
}