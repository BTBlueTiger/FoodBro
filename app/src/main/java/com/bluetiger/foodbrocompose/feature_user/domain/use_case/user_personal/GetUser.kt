package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalFoodBroRepository

class GetUser(
    private val repository: UserPersonalFoodBroRepository
) {
    suspend operator fun invoke(email: String) : UserPersonal? = repository.getUserByName(email)
}