package com.bluetiger.foodbrocompose.feature_user.domain.use_case

import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFoodBroRepository

class GetCurrentUser(
    private val repository: UserFoodBroRepository
) {
    operator fun invoke(): User? {
        return repository.flowUser.value
    }
    fun notNull(): Boolean {
        return invoke() != null
    }
}