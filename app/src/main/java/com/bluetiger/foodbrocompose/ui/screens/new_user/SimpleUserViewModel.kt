package com.bluetiger.foodbrocompose.ui.screens.new_user

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.ui.common.stateable.StateAble
import com.bluetiger.foodbrocompose.user.PersonDataValueType

class NewUserViewModel : ViewModel() {


    val person = mutableStateMapOf(
        Pair(PersonDataValueType.NAME, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.HEIGHT, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.WEIGHT, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.DATE, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.GENDER, StateAble.Model("", StateAble.State.DEFAULT)),
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