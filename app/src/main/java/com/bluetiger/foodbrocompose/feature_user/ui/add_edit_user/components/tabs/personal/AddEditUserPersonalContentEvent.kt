package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.personal

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation

sealed class AddEditUserPersonalContentEvent {
    data class EnteredValue<T : Any>(
        val value: T,
        val enteredValueType: UserPersonalInformation.ValueType
    ) : AddEditUserPersonalContentEvent()

    object OnChangeTab : AddEditUserPersonalContentEvent()

}