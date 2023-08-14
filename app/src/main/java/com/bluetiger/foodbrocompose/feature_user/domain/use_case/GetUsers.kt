package com.bluetiger.foodbrocompose.feature_user.domain.use_case

import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsers(
    private val repository: UserRepository
) {

    operator fun invoke() : Flow<List<User>> = repository.getUsers()

}