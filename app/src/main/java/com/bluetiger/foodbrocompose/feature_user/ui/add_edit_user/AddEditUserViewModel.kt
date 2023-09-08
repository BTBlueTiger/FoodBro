package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state.ConditionOutlineTextFieldPack
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors.OutlineTextFieldColorCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

@HiltViewModel
class AddEditUserViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "AddEditUser"

    private val _personalInformation =
        mutableStateMapOf<User.ValueType, ConditionOutlineTextFieldPack<Any>>()

    val personalInformation: SnapshotStateMap<User.ValueType, ConditionOutlineTextFieldPack<Any>> =
        _personalInformation

    init {
        User.ValueType.values().associateWith {
            ConditionOutlineTextFieldPack(it.dataType.defaultValue())
        }.toMap().forEach {
            _personalInformation[it.key] = it.value
        }
    }

    private fun KClass<*>.defaultValue() = when (this) {
        String::class -> ""
        Int::class -> 0
        Long::class -> 0
        Gender::class -> Gender.NONE
        else -> throw IllegalArgumentException("$this is not captured in default values!")
    }

    private inline fun <reified T : Any> personalInformationSetValue(
        valueType: User.ValueType,
        value: T
    ) {
        personalInformation[valueType]?.let {
            _personalInformation[valueType] = it.copy(value)
        }
    }

    private val _onSaveUserRequest = mutableStateOf(OnSaveUserResult())
    val onSaveUserRequest: State<OnSaveUserResult> = _onSaveUserRequest

    fun onEvent(event: AddEditUserEvent) {
        when (event) {

            is AddEditUserEvent.EnteredValue<*> -> {
                personalInformationSetValue(event.enteredValueType, event.value)
                _personalInformation[event.enteredValueType]?.isValid
            }


            is AddEditUserEvent.EditUser -> {
                this.viewModelScope.launch {
                    val user = userUseCases.getUser(event.name)
                    if (user != null) {
                        User.ValueType.values().forEach {
                            personalInformationSetValue(it, user.iterator().next())
                        }
                    }
                }
            }

            is AddEditUserEvent.SaveUser -> {
                viewModelScope.launch {

                    if (_personalInformation.filter { it.value.isValid }.isEmpty()) {
                        val user = User(
                            name = personalInformation[User.ValueType.NAME]!!.toValue(),
                            height = personalInformation[User.ValueType.HEIGHT]!!.toValue(),
                            weight = personalInformation[User.ValueType.WEIGHT]!!.toValue(),
                            gender = personalInformation[User.ValueType.GENDER]!!.toValue(),
                            birthday = personalInformation[User.ValueType.BIRTHDAY]!!.toValue()
                        )
                        userUseCases.addUser(user)

                        _onSaveUserRequest.value = onSaveUserRequest.value.copy(
                            success = true,
                            snackBarMessage = null
                        )

                    } else {
                        _onSaveUserRequest.value = onSaveUserRequest.value.copy(
                            success = false
                        )
                    }
                }
            }
        }
    }

    data class OnSaveUserResult(
        val snackBarMessage: String? = null,
        val success: Boolean = false
    )

    private inline fun <reified T> MutableState<ConditionOutlineTextFieldPack<T>>.isValid(
        valueType: User.ValueType,
        state: State<ConditionOutlineTextFieldPack<T>>
    ): Boolean {
        Log.e(TAG, valueType.toString())

        when (valueType) {
            User.ValueType.NAME -> {
                return if ((this.value.value as String).isEmpty()) {
                    this.value = state.value.copy(
                        isError = true,
                        supportingText = { Text(text = "A name is required") }
                    )
                    false
                } else {
                    this.value = state.value.copy(
                        isError = false,
                        supportingText = null
                    )
                    true
                }
            }

            User.ValueType.HEIGHT -> {
                if (this.value.value as Int == 0) {
                    this.value = state.value.copy(
                        isError = true,
                        supportingText = { Text(text = "The height have to be set") }
                    )
                    return false
                } else if (this.value.value as Int >= User.MAX_HEIGHT) {
                    this.value = state.value.copy(
                        isError = true,
                        supportingText = { Text(text = "This height have to be a mistake") }
                    )
                    return false
                } else {
                    this.value = state.value.copy(
                        isError = false,
                        supportingText = null
                    )
                    return true
                }
            }

            User.ValueType.WEIGHT -> {
                
                if (this.value.value as Int == 0) {
                    this.value = state.value.copy(
                        isError = true,
                        supportingText = { Text(text = "The weight have to be set") }
                    )
                    return false
                } else if (this.value.value as Int >= User.MAX_WEIGHT) {
                    this.value = state.value.copy(
                        isError = true,
                        supportingText = { Text(text = "This weight have to be a mistake") }
                    )
                    return false
                } else {
                    this.value = state.value.copy(
                        isError = false,
                        supportingText = null
                    )
                    return true
                }
            }

            User.ValueType.GENDER -> {
                return if (value.value == Gender.NONE) {
                    this.value = state.value.copy(
                        isError = true
                    )
                    _onSaveUserRequest.value = onSaveUserRequest.value.copy(
                        snackBarMessage = "Gender has to be set",
                        success = false
                    )
                    false
                } else {
                    this.value = state.value.copy(
                        isError = false
                    )
                    _onSaveUserRequest.value = onSaveUserRequest.value.copy(
                        snackBarMessage = null,
                        success = true
                    )
                    true
                }
            }

            User.ValueType.BIRTHDAY -> {
                return if (value.value == 0L) {
                    this.value = state.value.copy(
                        isError = true,
                        supportingText = { Text(text = "We need your birthday for calculations") }
                    )
                    false
                } else {
                    this.value = state.value.copy(
                        isError = false,
                        supportingText = null
                    )
                    true
                }
            }
        }
    }

    private inline fun <reified T> MutableState<ConditionOutlineTextFieldPack<T>>.checkValue(state: State<ConditionOutlineTextFieldPack<T>>) {

        if (T::class == String::class) {
            if ((value.value as String).isEmpty()) {
                this.value = state.value.copy(
                    supportingText = null,
                    isError = false,
                    colorCase = OutlineTextFieldColorCases.DEFAULT
                )
            } else {
                this.value = state.value.copy(
                    supportingText = null,
                    isError = false,
                    colorCase = OutlineTextFieldColorCases.VALID
                )
            }
        }

        if (T::class == Int::class || T::class == Long::class) {
            if ((this.value.value as Number) == 0) {
                this.value = state.value.copy(
                    supportingText = null,
                    isError = false,
                    colorCase = OutlineTextFieldColorCases.DEFAULT
                )
            } else {
                this.value = state.value.copy(
                    supportingText = null,
                    isError = false,
                    colorCase = OutlineTextFieldColorCases.VALID
                )
            }
        }
    }
}

