package com.bluetiger.foodbrocompose.feature_user.domain.use_case

import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFoodBroRepository

class GetUser(
    private val repository: UserFoodBroRepository
) {
    suspend operator fun invoke(email: String) : User? = repository.getUserByName(email)
}