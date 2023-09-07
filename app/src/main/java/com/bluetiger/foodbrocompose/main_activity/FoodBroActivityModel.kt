package com.bluetiger.foodbrocompose.main_activity

import android.Manifest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFoodBroRepository
import com.bluetiger.foodbrocompose.permission.use_case.PermissionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class FoodBroActivityModel @Inject constructor(
    private val permissionUseCases: PermissionUseCases,
    private val userRepository: UserFoodBroRepository
) : ViewModel() {


    val user = userRepository.flowUser

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