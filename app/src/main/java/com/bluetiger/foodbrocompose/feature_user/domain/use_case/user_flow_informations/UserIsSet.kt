package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations

import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFlowRepository
import kotlin.reflect.KProperty

class UserIsSet(
    private val repository: UserFlowRepository
) {

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean){
        repository.setCurrentUserIsSet(value)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : Boolean {
        return repository.currentUserIsSet.value!!
    }

}