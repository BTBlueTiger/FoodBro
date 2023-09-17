package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserMap
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFlowRepository
import kotlin.reflect.KProperty

class NewUser(
    private val repository: UserFlowRepository
) {
    fun setValue(value: UserMap){
        repository.setNewUser(value)
    }
    fun getValue(): UserMap {
        return repository.newUser.value!!
    }

}