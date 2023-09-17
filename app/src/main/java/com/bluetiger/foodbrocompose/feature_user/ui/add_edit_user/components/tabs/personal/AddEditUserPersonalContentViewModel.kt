package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.personal

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

    private val pendingMap by lazy { userUseCases.pendingInformation.newUser.getValue() }
    private val newUserPersonal by lazy { pendingMap[UserPersonalInformation::class] as UserPersonalInformation }

    private val TAG = "AddEditUser"

    private val _personalInformation =
        UserPersonalInformation.ValueType.values().map {
            it to ConditionOutlineTextFieldPack(it.dataType.defaultValue())
        }.toMutableStateMap()

    val personalInformation: SnapshotStateMap<UserPersonalInformation.ValueType, ConditionOutlineTextFieldPack<Any>> =
        _personalInformation


    init {
        if (pendingMap.isPending) {
            val iterator = newUserPersonal.iterator()
            UserPersonalInformation.ValueType.values().forEach {
                personalInformation.copy(it, iterator.next())
            }
        }
    }


    fun onEvent(event: AddEditUserPersonalContentEvent) {
        when (event) {
            is AddEditUserPersonalContentEvent.EnteredValue<*> -> {

                personalInformation.copy(event.enteredValueType, event.value)
                userUseCases.pendingInformation.newUser.getValue().isPending = true

                pendingMap[UserPersonalInformation::class] =
                    newUserPersonal.customCopy(
                        event.enteredValueType.memberParam,
                        event.value
                    ) as UserPersonalInformation
                userUseCases.pendingInformation.newUser.setValue(pendingMap)
            }

            is AddEditUserPersonalContentEvent.OnChangeTab -> {

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
                        isError = false
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
                        colorCase = OutlineTextFieldColorCases.VALID
                    )
                }
            }
        }
    }
}

