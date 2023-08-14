package com.bluetiger.foodbrocompose.feature_user.domain.repository

import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    suspend fun getUserByEmail(email: String): User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

}