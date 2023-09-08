package com.bluetiger.foodbrocompose.feature_user.domain.repository

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class UserPersonalFoodBroRepository {

    private val _flowUserPersonal = MutableStateFlow<UserPersonal?>(null)
    val flowUserPersonal: StateFlow<UserPersonal?> = _flowUserPersonal

    fun setUser(userPersonal: UserPersonal?){
        _flowUserPersonal.value = userPersonal
    }

    abstract fun getUsers(): Flow<List<UserPersonal>>

    abstract suspend fun getUserByName(name: String): UserPersonal?

    abstract suspend fun insertUser(userPersonal: UserPersonal)

    abstract suspend fun deleteUser(userPersonal: UserPersonal)

}