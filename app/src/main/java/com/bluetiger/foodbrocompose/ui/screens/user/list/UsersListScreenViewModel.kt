package com.bluetiger.foodbrocompose.ui.screens.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.Graph
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.database.user.User
import com.bluetiger.foodbrocompose.database.user.UserStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersListScreenViewModel(
    private val userStore: UserStore = Graph.userStore
) : ViewModel() {

    fun onUserChangeRequest(user: User){
        FBPreferences.getInstance().setUserEmail(user.email)
        FBPreferences.getInstance().apply {
            setUserEmail(user.email)
            setUserIsSet(true)
        }
        Graph.setGlobalUser(user = user)
    }

    private val _users = MutableStateFlow(listOf<User>())
    val users: StateFlow<List<User>>
        get() = _users

    init {
        viewModelScope.launch {
            userStore.getAllUser().collect{
                _users.value = it
            }
        }
    }
}