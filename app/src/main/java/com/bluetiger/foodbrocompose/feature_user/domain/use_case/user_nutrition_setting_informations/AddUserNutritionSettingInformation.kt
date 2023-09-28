package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_nutrition_setting_informations

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserNutritionSettingInformationRepository

class AddUserNutritionSettingInformation(
    val repository: UserNutritionSettingInformationRepository
) {
    suspend operator fun invoke(nutritionSettingInformation: UserNutritionSettingInformation){
        repository.insertUserNutrientSetting(nutritionSettingInformation)
    }
}