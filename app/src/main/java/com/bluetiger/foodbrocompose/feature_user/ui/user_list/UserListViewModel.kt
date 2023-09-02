package com.bluetiger.foodbrocompose.feature_user.ui.user_list

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    fun getUser() = userRepository.getUsers()

}