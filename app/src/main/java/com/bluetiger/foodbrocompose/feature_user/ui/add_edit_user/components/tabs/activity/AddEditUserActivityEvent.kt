package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation

sealed class AddEditUserActivityEvent {
    data class PreconfiguredActivityValueChanged(
        val preconfiguredType: UserActivityInformation.ValueType
    ) : AddEditUserActivityEvent()

    data class CustomActivityValueChanged(
        val userActivityInformationValueType: UserActivityInformation.ValueType,
        val value: Float
    ) : AddEditUserActivityEvent()

    data class SelectedOptionChanged(
        val option: ActivitySettingsType
    ) : AddEditUserActivityEvent()
}