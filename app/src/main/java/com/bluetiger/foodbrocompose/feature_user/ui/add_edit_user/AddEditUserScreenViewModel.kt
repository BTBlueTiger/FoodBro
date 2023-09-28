package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations.UserActivityInformationUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.UserPersonalInformationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserScreenViewModel @Inject constructor(
    private val userPersonalInformationUseCases: UserPersonalInformationUseCases,
    private val activityUseCases: UserActivityInformationUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {



    fun onEvent(event: AddEditUserEvent)  {
        when (event) {
            is AddEditUserEvent.InitNewUser -> {
                val pendingUser =
                    userPersonalInformationUseCases.getPendingNewUserPersonalInformation()
                if (pendingUser == null) {
                    userPersonalInformationUseCases.initPendingNewUserPersonalInformation()
                } else {
                    Log.i("Old Pending User",
                        userPersonalInformationUseCases.getPendingNewUserPersonalInformation()
                            .toString()
                    )
                }
            }

            else -> {}
        }
    }

}