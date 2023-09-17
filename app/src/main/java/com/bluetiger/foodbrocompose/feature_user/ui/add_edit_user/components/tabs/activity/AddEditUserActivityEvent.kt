package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation

sealed class AddEditUserActivityEvent {
    data class ActivityValueChanged(
        val userActivityInformationValueType: UserActivityInformation.ValueType,
        val value : Float,
        val activitySettingsType: ActivitySettingsType
    ) : AddEditUserActivityEvent()
}