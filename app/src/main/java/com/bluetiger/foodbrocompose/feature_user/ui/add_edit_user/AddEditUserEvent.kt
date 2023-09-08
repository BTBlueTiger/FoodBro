package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal

sealed class AddEditUserEvent {

    data class EnteredValue<T : Any>(
        val value: T,
        val enteredValueType: UserPersonal.ValueType
    ) :
        AddEditUserEvent()

    data class EditUser(val name: String) : AddEditUserEvent()

    object SaveUser : AddEditUserEvent()

}
