package com.bluetiger.foodbrocompose.feature_user.domain.use_case
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository


class SetCurrentUser(
    private val repository: UserRepository
) {
    operator fun invoke(user: User){
        repository.setUser(user = user)
    }
}