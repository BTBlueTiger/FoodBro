package com.bluetiger.foodbrocompose.feature_user.data.repository

import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFoodBroRepository
import kotlinx.coroutines.flow.Flow

class UserFoodBroRepositoryImpl(
    private val dao: UserDao
): UserFoodBroRepository() {

    override fun getUsers(): Flow<List<User>> {
        return dao.getAllUser()
    }

    override suspend fun getUserByName(name: String): User? {
        return dao.getUserByName(name)
    }

    override suspend fun insertUser(user: User) {
        dao.insert(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.delete(user)
    }
}