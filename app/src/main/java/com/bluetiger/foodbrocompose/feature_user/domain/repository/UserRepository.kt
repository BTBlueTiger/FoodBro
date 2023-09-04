package com.bluetiger.foodbrocompose.feature_user.domain.repository

import android.util.Log
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class UserRepository {

    private val _flowUser = MutableStateFlow<User?>(null)
    val flowUser: StateFlow<User?> = _flowUser

    fun setUser(user: User?){
        _flowUser.value = user
    }


    abstract fun getUsers(): Flow<List<User>>

    abstract suspend fun getUserByEmail(email: String): User?

    abstract suspend fun insertUser(user: User)

    abstract suspend fun deleteUser(user: User)

}