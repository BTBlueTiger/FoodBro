package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.nutrition_settings

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation

sealed class AddEditUserNutritionSettingsEvent {

    data class OnOptionChanged(
        val option: UserNutritionSettingInformation.Option
    ) : AddEditUserNutritionSettingsEvent()

    data class MacroValueChanged(
        val option: UserNutritionSettingInformation.ValueType,
        val value: Int
    ) : AddEditUserNutritionSettingsEvent()

}
