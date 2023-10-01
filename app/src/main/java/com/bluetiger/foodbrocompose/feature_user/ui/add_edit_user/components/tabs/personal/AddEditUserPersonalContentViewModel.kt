package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.personal

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.defaultValue
import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state.ConditionOutlineTextFieldPack
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors.OutlineTextFieldColorCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserPersonalContentViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = this::class.toString()

    private val pendingMap by lazy { userUseCases.pendingInformation.newUser.getValue() }
    private var newUserPersonal =
        pendingMap[UserPersonalInformation::class] as UserPersonalInformation



    private val _personalInformation =
        UserPersonalInformation.ValueType.values().map {
            it to ConditionOutlineTextFieldPack(it.dataType.defaultValue())
        }.toMutableStateMap()

    val personalInformation: SnapshotStateMap<UserPersonalInformation.ValueType, ConditionOutlineTextFieldPack<Any>> =
        _personalInformation


    private fun <T> UserPersonalInformation.ValueType.isNotEmpty(value: T) : Boolean{
        when(this.dataType){
            String::class -> {
                return value != ""
            }
            Long::class -> {
                return value as Long != 0L
            }
            Int::class -> {
                return value as Int != 0
            }
        }
        return false
    }

    init {
        if (pendingMap.isPending) {
            newUserPersonal.iterator().forEach {
                if(it.first.isNotEmpty(it.second)){
                    personalInformation.copy(it.first, it.second)
                }
            }
            Log.i(TAG, "Saved PersonalInformation= $personalInformation")
        }
    }

    fun onEvent(event: AddEditUserPersonalContentEvent) {
        when (event) {
            is AddEditUserPersonalContentEvent.EnteredValue<*> -> {
                userUseCases.pendingInformation.newUser.getValue().isPending = true

                personalInformation.copy(event.enteredValueType, event.value)
                newUserPersonal = newUserPersonal.customCopy(
                    event.enteredValueType.memberParam, event.value
                ) as UserPersonalInformation
                pendingMap[UserPersonalInformation::class] = newUserPersonal
                Log.i(TAG, newUserPersonal.toString())
                userUseCases.pendingInformation.newUser.setValue(pendingMap)
                if(personalInformation.values.none { it.isError } && personalInformation.values.all { it.isValid }){
                    userUseCases.pendingInformation.newUser.setPersonalInformationIsSet(true)
                } else {
                    personalInformation.filter { !it.value.isValid }.forEach {
                        Log.e("Tag", it.key.label)
                    }

                    userUseCases.pendingInformation.newUser.setPersonalInformationIsSet(false)
                }
            }
        }
    }

    private fun <T> SnapshotStateMap<UserPersonalInformation.ValueType, ConditionOutlineTextFieldPack<T>>.copy(
        valueType: UserPersonalInformation.ValueType,
        value: T
    ) {
        when (valueType) {
            UserPersonalInformation.ValueType.NAME -> {
                this[valueType] = if ((value as String).isEmpty()) {
                    this[valueType]!!.copy(
                        value = value,
                        isError = true,
                        supportingText = { Text(text = "A name is required") }
                    )
                } else {
                    this[valueType]!!.copy(
                        value = value,
                        isError = false,
                        supportingText = null,
                        isValid = true,
                        colorCase = OutlineTextFieldColorCases.VALID
                    )
                }
            }

            UserPersonalInformation.ValueType.HEIGHT -> {
                this[valueType] = if (value as Int == 0) {
                    this[valueType]!!.copy(
                        value = value,
                        isError = true,
                        supportingText = { Text(text = "The height have to be set") }
                    )
                } else if (value as Int >= UserPersonalInformation.MAX_HEIGHT) {
                    this[valueType]!!.copy(
                        value = value,
                        isError = true,
                        supportingText = { Text(text = "This height have to be a mistake") }
                    )
                } else {
                    this[valueType]!!.copy(
                        value = value,
                        isError = false,
                        supportingText = null,
                        isValid = true,
                        colorCase = OutlineTextFieldColorCases.VALID
                    )
                }
            }

            UserPersonalInformation.ValueType.WEIGHT -> {

                this[valueType] = if (value as Int == 0) {
                    this[valueType]!!.copy(
                        value = value,
                        isError = true,
                        supportingText = { Text(text = "The weight have to be set") }
                    )
                } else if (value as Int >= UserPersonalInformation.MAX_WEIGHT) {
                    this[valueType]!!.copy(
                        value = value,
                        isError = true,
                        supportingText = { Text(text = "This weight have to be a mistake") }
                    )
                } else {
                    this[valueType]!!.copy(
                        value = value,
                        isError = false,
                        supportingText = null,
                        isValid = true,
                        colorCase = OutlineTextFieldColorCases.VALID
                    )
                }
            }

            UserPersonalInformation.ValueType.GENDER -> {
                this[valueType] = if (value == Gender.NONE) {
                    this[valueType]!!.copy(
                        value = value,
                        isError = true
                    )
                } else {
                    this[valueType]!!.copy(
                        value = value,
                        isError = false,
                        isValid = true
                    )
                }
            }

            UserPersonalInformation.ValueType.BIRTHDAY -> {

                this[valueType] = if (value as Long == 0L) {
                    this[valueType]!!.copy(
                        value = value,
                        isError = true,
                        supportingText = { Text(text = "We need your birthday for calculations") }
                    )
                } else {
                    this[valueType]!!.copy(
                        value = value,
                        isError = false,
                        supportingText = null,
                        isValid = true,
                        colorCase = OutlineTextFieldColorCases.VALID
                    )
                }
            }
        }
    }
}

