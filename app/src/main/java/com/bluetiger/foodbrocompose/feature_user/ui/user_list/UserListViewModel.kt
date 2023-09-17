package com.bluetiger.foodbrocompose.feature_user.ui.user_list

import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.database.FBPreferences.Companion.getInstance
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.UserPersonalInformationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userPersonalInformationUseCases: UserPersonalInformationUseCases
) : ViewModel() {

    private val preferences : FBPreferences = getInstance()

    fun getUsers() = userPersonalInformationUseCases.getAllLocalUserPersonalInformation()

    fun onEvent(event: UserListEvents) = when(event) {
        is UserListEvents.SetUser -> {
            //userPersonalInformationUseCases.setPendingNewUserPersonalInformation(event.userPersonalInformation)
        }
    }

}