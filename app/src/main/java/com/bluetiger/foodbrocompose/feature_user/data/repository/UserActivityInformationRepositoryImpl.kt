package com.bluetiger.foodbrocompose.feature_user.data.repository

import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserActivityInformationDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserActivityInformationRepository

class UserActivityInformationRepositoryImpl(
    private val dao: UserActivityInformationDao
) : UserActivityInformationRepository() {
    override suspend fun getUserActivityLevels(userPersonalInformation: UserPersonalInformation): UserActivityInformation {
        return dao.getUserActivityLevels(userPersonalInformation.name)
    }

    override suspend fun insertActivityLevel(activity: UserActivityInformation) {
        dao.insert(activity)
    }

    override suspend fun deleteUserActivity(activity: UserActivityInformation) {
        dao.delete(activity)
    }
}