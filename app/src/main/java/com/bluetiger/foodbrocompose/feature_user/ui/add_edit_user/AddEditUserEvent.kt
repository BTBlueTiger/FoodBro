package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import com.bluetiger.foodbrocompose.feature_user.domain.model.User

sealed class AddEditUserEvent {

    data class EnteredValue<T>(val value: T, val enteredValueType: User.ValueType) :
        AddEditUserEvent()

    data class EditUser(val name: String) : AddEditUserEvent()

    object SaveUser : AddEditUserEvent()

}
