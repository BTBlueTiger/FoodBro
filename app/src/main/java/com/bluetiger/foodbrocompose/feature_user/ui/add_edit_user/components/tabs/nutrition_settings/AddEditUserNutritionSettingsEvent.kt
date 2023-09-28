package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.nutrition_settings

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSetting

sealed class AddEditUserNutritionSettingsEvent {

    data class OnOptionChanged(
        val option: UserNutritionSetting.Option
    ) : AddEditUserNutritionSettingsEvent()

    data class MacroValueChanged(
        val option: UserNutritionSetting.ValueType,
        val value: Int
    ) : AddEditUserNutritionSettingsEvent()

}
