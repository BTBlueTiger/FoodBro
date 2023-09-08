package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivity
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserActivityFoodBroRepository

class GetUserActivity(
    private val repository: UserActivityFoodBroRepository
) {
    suspend operator fun invoke(user: UserPersonal) : UserActivity{
        return repository.getUserActivityLevels(userPersonal = user)
    }
}