package com.bluetiger.foodbrocompose.ui.screens.user.create

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.Graph
import com.bluetiger.foodbrocompose.ui.common.stateable.StateAble
import com.bluetiger.foodbrocompose.database.user.PersonDataValueType
import com.bluetiger.foodbrocompose.database.user.User
import com.bluetiger.foodbrocompose.database.user.UserStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewUserViewModel(
    private val userStore: UserStore = Graph.userStore
) : ViewModel() {

    data class User(
        val email: String? = null
    )

    private val _user = MutableStateFlow(User())

    val user: StateFlow<User>
        get() = _user

    fun onCreateNewUserClicked() {

        viewModelScope.launch {
            userStore.getUserByEmail(getValue(PersonDataValueType.EMAIL)).collect{
                if(it == null){
                    userStore.addUser(User(
                        email = getValue(PersonDataValueType.EMAIL),
                        height = getValue(PersonDataValueType.HEIGHT).toInt(),
                        weight = getValue(PersonDataValueType.WEIGHT).toInt(),
                        birthday = getValue(PersonDataValueType.BIRTHDAY).toLong(),
                        gender = getValue(PersonDataValueType.GENDER)
                    ))
                } else {
                    Log.e("User", it.toString())
                }
            }
        }
    }

    val person = mutableStateMapOf(
        Pair(PersonDataValueType.EMAIL, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.HEIGHT, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.WEIGHT, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.BIRTHDAY, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.GENDER, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.PREGNANT, StateAble.Model("", StateAble.State.FILLED)),
        Pair(PersonDataValueType.BREAST_FEEDING, StateAble.Model("", StateAble.State.FILLED))
        )

    fun setValue(valueType: PersonDataValueType, value: String) {
        person[valueType]?.setValue(value)
        Log.e("State", value)
        if(value.isEmpty()){
            setState(valueType, StateAble.State.DEFAULT)
        } else {
            setState(valueType, StateAble.State.FILLED)
        }
    }

    fun setState(valueType: PersonDataValueType, state: StateAble.State) {
        person[valueType]?.setState(state)
    }
    fun getValue(valueType: PersonDataValueType): String = person[valueType]?.getValue() ?: ""
    fun getState(valueType: PersonDataValueType): StateAble.State =
        person[valueType]?.getState() ?: StateAble.State.DEFAULT
    fun getModel(valueType: PersonDataValueType): StateAble.Model? = person[valueType]

}