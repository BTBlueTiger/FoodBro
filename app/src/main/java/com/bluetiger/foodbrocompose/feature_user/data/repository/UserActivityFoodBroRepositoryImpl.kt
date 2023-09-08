package com.bluetiger.foodbrocompose.feature_user.data.repository

import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserActivityDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivity
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserActivityFoodBroRepository

class UserActivityFoodBroRepositoryImpl(
    private val dao: UserActivityDao
) : UserActivityFoodBroRepository() {
    override suspend fun getUserActivityLevels(userPersonal: UserPersonal): UserActivity {
        return dao.getUserActivityLevels(userPersonal.name)
    }

    override suspend fun insertActivityLevel(activity: UserActivity) {
        dao.insert(activity)
    }

    override suspend fun deleteUserActivity(activity: UserActivity) {
        dao.delete(activity)
    }
}