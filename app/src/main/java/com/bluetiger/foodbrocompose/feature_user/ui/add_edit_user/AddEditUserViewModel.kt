package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors.OutlineTextFieldColorCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditUserViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = "AddEditUser"

    private val _email = mutableStateOf(InputFieldState(""))
    val email: State<InputFieldState<String>> = _email

    private val _height = mutableStateOf(InputFieldState(0))
    val height: State<InputFieldState<Int>> = _height

    private val _weight = mutableStateOf(InputFieldState(0))
    val weight: State<InputFieldState<Int>> = _weight

    private val _gender = mutableStateOf(InputFieldState(Gender.NONE))
    val gender: State<InputFieldState<Gender>> = _gender

    private val _birthday = mutableStateOf(InputFieldState(0L))
    val birthday: State<InputFieldState<Long>> = _birthday

    private val _onSaveUserRequest = mutableStateOf(OnSaveUserResult())
    val onSaveUserRequest: State<OnSaveUserResult> = _onSaveUserRequest

    private var currentUserEmail: String? = null

    init {
        savedStateHandle.get<String>("userEmail")?.let { mail ->
            if (mail.isNotEmpty()) {
                viewModelScope.launch {
                    userUseCases.getUser(mail)?.also { user ->
                        currentUserEmail = user.email

                        _email.value = _email.value.copy(
                            value = user.email
                        )

                        _height.value = _height.value.copy(
                            value = user.height
                        )

                        _weight.value = _weight.value.copy(
                            value = user.weight
                        )

                        _birthday.value = _birthday.value.copy(
                            value = user.birthday
                        )

                        _gender.value = _gender.value.copy(
                            value = user.gender
                        )
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditUserEvent) {
        when (event) {
            is AddEditUserEvent.EnteredValue<*> -> {

                when (event.enteredValueType) {
                    User.ValueType.EMAIL -> {
                        _email.value = email.value.copy(
                            value = event.value as String,
                        )
                        _email.checkValue(email)
                    }

                    User.ValueType.HEIGHT -> {
                        _height.value =
                            height.value.copy(
                                value = event.value as Int
                            )
                        _height.checkValue(height)
                    }

                    User.ValueType.WEIGHT -> {
                        _weight.value =
                            weight.value.copy(
                                value = event.value as Int
                            )
                        _weight.checkValue(weight)
                    }

                    User.ValueType.GENDER -> {
                        _gender.value = gender.value.copy(
                            value = event.value as Gender
                        )
                        _gender.checkValue(gender)
                    }

                    User.ValueType.BIRTHDAY -> {
                        _birthday.value = birthday.value.copy(
                            value = event.value as Long
                        )
                        _birthday.checkValue(birthday)
                    }
                }
            }

            is AddEditUserEvent.SaveUser -> {
                viewModelScope.launch {

                    Log.e(TAG, birthday.value.toString())
                    if (_email.isValid(User.ValueType.EMAIL, email) &&
                        _height.isValid(User.ValueType.HEIGHT, height) &&
                        _weight.isValid(User.ValueType.WEIGHT, weight) &&
                        _gender.isValid(User.ValueType.GENDER, gender) &&
                        _birthday.isValid(User.ValueType.BIRTHDAY, birthday)
                    ) {
                        userUseCases.addUser(
                            User(
                                email = email.value.value,
                                height = height.value.value,
                                weight = weight.value.value,
                                gender = gender.value.value,
                                birthday = birthday.value.value
                            )
                        )
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
        val snackBarMessage : String? = null,
        val success: Boolean = false
    )

    private inline fun <reified T> MutableState<InputFieldState<T>>.isValid(
        valueType: User.ValueType,
        state: State<InputFieldState<T>>
    ): Boolean {
        Log.e(TAG, valueType.toString())

        when (valueType) {
            User.ValueType.EMAIL -> {
                if ((this.value.value as String).isEmpty()) {
                    this.value = state.value.copy(
                        isError = true,
                        supportingText = { Text(text = "A email address is required") }
                    )
                    return false
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(this.value.value as String)
                        .matches()
                ) {
                    this.value = state.value.copy(
                        isError = true,
                        supportingText = { Text(text = "This is not a valid email address") }
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

    private inline fun <reified T> MutableState<InputFieldState<T>>.checkValue(state: State<InputFieldState<T>>) {

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

