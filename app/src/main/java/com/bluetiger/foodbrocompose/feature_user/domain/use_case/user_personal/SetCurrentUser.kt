package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalFoodBroRepository


class SetCurrentUser(
    private val repository: UserPersonalFoodBroRepository
) {
    operator fun invoke(userPersonal: UserPersonal){
        repository.setUser(userPersonal = userPersonal)
    }
}