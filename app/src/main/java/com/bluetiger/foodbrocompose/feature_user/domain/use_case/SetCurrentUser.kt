package com.bluetiger.foodbrocompose.feature_user.domain.use_case
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFoodBroRepository


class SetCurrentUser(
    private val repository: UserFoodBroRepository
) {
    operator fun invoke(user: User){
        repository.setUser(user = user)
    }
}