package com.bluetiger.foodbrocompose.main_activity

import android.Manifest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalFoodBroRepository
import com.bluetiger.foodbrocompose.permission.use_case.PermissionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class FoodBroActivityModel @Inject constructor(
    private val permissionUseCases: PermissionUseCases,
    private val userRepository: UserPersonalFoodBroRepository
) : ViewModel() {


    val user = userRepository.flowUserPersonal

    fun onEvent(event: FoodBroActivityEvent) {
        when (event) {

            is FoodBroActivityEvent.Logout -> {
                userRepository.setUser(null)
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