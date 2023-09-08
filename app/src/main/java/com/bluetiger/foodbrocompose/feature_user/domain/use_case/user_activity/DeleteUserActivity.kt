package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivity
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserActivityFoodBroRepository

class DeleteUserActivity(
    private val repository: UserActivityFoodBroRepository
) {
    suspend operator fun invoke(userActivity: UserActivity){
        repository.deleteUserActivity(userActivity)
    }
}