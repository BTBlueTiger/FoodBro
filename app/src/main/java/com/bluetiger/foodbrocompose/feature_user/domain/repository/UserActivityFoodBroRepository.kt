package com.bluetiger.foodbrocompose.feature_user.domain.repository

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivity

abstract class UserActivityFoodBroRepository {
    abstract suspend fun getUserActivityLevels(userPersonal: UserPersonal) : UserActivity
    abstract suspend fun insertActivityLevel(activity: UserActivity)
    abstract suspend fun deleteUserActivity(activity: UserActivity)

}