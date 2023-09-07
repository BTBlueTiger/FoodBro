package com.bluetiger.foodbrocompose.feature_user.ui.user_list

import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.database.FBPreferences.Companion.getInstance
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val preferences : FBPreferences = getInstance()

    fun getUsers() = userUseCases.getUsers()

    fun onEvent(event: UserListEvents) = when(event) {
        is UserListEvents.SetUser -> {
            userUseCases.setCurrentUser(event.user)
        }
    }

}