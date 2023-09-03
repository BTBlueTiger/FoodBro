package com.bluetiger.foodbrocompose.main_activity

import android.Manifest
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository
import com.bluetiger.foodbrocompose.permission.use_case.PermissionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class FoodBroActivityModel @Inject constructor(
    private val permissionUseCases: PermissionUseCases,
    private val userRepository: UserRepository
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