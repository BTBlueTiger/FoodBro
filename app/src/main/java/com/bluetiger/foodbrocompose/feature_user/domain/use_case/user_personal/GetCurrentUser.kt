package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalFoodBroRepository

class GetCurrentUser(
    private val repository: UserPersonalFoodBroRepository
) {
    operator fun invoke(): UserPersonal? {
        return repository.flowUserPersonal.value
    }
    fun notNull(): Boolean {
        return invoke() != null
    }
}