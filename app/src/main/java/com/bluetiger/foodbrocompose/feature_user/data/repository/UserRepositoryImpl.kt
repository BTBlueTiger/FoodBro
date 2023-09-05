package com.bluetiger.foodbrocompose.feature_user.data.repository

import androidx.compose.runtime.mutableStateOf
import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserRepositoryImpl(
    private val dao: UserDao
): UserRepository() {

    override fun getUsers(): Flow<List<User>> {
        return dao.getAllUser()
    }

    override suspend fun getUserByEmail(email: String): User? {
        return dao.getUserByEmail(email)
    }

    override suspend fun insertUser(user: User) {
        dao.insert(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.delete(user)
    }
}