package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalFoodBroRepository
import kotlinx.coroutines.flow.Flow

class GetUsers(
    private val repository: UserPersonalFoodBroRepository
) {

    operator fun invoke() : Flow<List<UserPersonal>> = repository.getUsers()

}