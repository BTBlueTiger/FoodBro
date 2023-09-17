package com.bluetiger.foodbrocompose.feature_user.data.repository

import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserPersonalInformationDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalInformationRepository
import kotlinx.coroutines.flow.Flow

class UserPersonalPersonalInformationRepositoryImpl(
    private val dao: UserPersonalInformationDao
): UserPersonalInformationRepository() {

    override fun getUsers(): Flow<List<UserPersonalInformation>> {
        return dao.getAllUser()
    }

    override suspend fun getUserByName(name: String): UserPersonalInformation? {
        return dao.getUserByName(name)
    }

    override suspend fun insertUser(userPersonalInformation: UserPersonalInformation) {
        dao.insert(userPersonalInformation)
    }

    override suspend fun deleteUser(userPersonalInformation: UserPersonalInformation) {
        dao.delete(userPersonalInformation)
    }
}