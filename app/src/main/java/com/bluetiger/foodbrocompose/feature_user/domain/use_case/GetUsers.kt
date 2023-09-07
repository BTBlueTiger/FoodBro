package com.bluetiger.foodbrocompose.feature_user.domain.use_case

import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFoodBroRepository
import kotlinx.coroutines.flow.Flow

class GetUsers(
    private val repository: UserFoodBroRepository
) {

    operator fun invoke() : Flow<List<User>> = repository.getUsers()

}