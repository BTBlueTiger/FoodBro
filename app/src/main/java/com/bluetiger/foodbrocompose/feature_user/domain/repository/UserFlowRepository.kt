package com.bluetiger.foodbrocompose.feature_user.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserMap

abstract class UserFlowRepository {



    private val _currentUserIsSet = MutableLiveData(false)
    val currentUserIsSet : LiveData<Boolean> = _currentUserIsSet

    fun setCurrentUserIsSet(boolean: Boolean){
        _currentUserIsSet.value = boolean
    }

    private val _currentUser = MutableLiveData(UserMap())
    val currentUser : LiveData<UserMap> = _currentUser

    fun setCurrentUser(currentUser: UserMap){
        _currentUser.value = currentUser
    }

    private val _newUser = MutableLiveData(UserMap())
    val newUser : LiveData<UserMap> = _newUser

    fun setNewUser(newUser: UserMap){
        _newUser.value = newUser
    }
}