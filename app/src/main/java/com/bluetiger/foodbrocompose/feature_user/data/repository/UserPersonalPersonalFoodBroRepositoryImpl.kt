package com.bluetiger.foodbrocompose.feature_user.data.repository

import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserPersonalDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalFoodBroRepository
import kotlinx.coroutines.flow.Flow

class UserPersonalPersonalFoodBroRepositoryImpl(
    private val dao: UserPersonalDao
): UserPersonalFoodBroRepository() {

    override fun getUsers(): Flow<List<UserPersonal>> {
        return dao.getAllUser()
    }

    override suspend fun getUserByName(name: String): UserPersonal? {
        return dao.getUserByName(name)
    }

    override suspend fun insertUser(userPersonal: UserPersonal) {
        dao.insert(userPersonal)
    }

    override suspend fun deleteUser(userPersonal: UserPersonal) {
        dao.delete(userPersonal)
    }
}