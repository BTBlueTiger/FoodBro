package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserMap
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFlowRepository
import kotlin.reflect.KProperty

class CurrentUser(
    private val repository: UserFlowRepository
) {
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: UserMap){
        repository.setCurrentUser(value)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : UserMap {
        return repository.currentUser.value!!
    }
}