package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserMap
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFlowRepository
import kotlin.reflect.KProperty

class NewUser(
    private val repository: UserFlowRepository
) {

    private val _personalInformationIsSet = MutableLiveData(false)
    val personalInformationIsSet : LiveData<Boolean> = _personalInformationIsSet

    fun setPersonalInformationIsSet(boolean: Boolean){
        _personalInformationIsSet.value = boolean
    }

    fun setValue(value: UserMap){
        repository.setNewUser(value)
    }
    fun getValue(): UserMap {
        return repository.newUser.value!!
    }

    fun clear() {
        setValue(UserMap())
    }

}