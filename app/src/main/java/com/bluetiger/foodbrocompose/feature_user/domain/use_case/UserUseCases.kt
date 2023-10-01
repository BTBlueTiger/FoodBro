package com.bluetiger.foodbrocompose.feature_user.domain.use_case

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations.UserActivityInformationUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations.UserFlowUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_nutrition_setting_informations.UserNutritionSettingsUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.UserPersonalInformationUseCases

data class UserUseCases (
    val pendingInformation : UserFlowUseCases,
    val personalInformation : UserPersonalInformationUseCases,
    val activityInformation : UserActivityInformationUseCases,
    val nutritionSettingInformation: UserNutritionSettingsUseCases,
)