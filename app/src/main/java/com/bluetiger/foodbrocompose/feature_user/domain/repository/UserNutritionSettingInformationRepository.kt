package com.bluetiger.foodbrocompose.feature_user.domain.repository

import com.bluetiger.foodbrocompose.common.FoodBroRepository
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation

abstract class UserNutritionSettingInformationRepository : FoodBroRepository {
    abstract suspend fun getUserNutrientInformation(userPersonalInformation: UserPersonalInformation) : UserNutritionSettingInformation
    abstract suspend fun insertUserNutrientSetting(setting: UserNutritionSettingInformation)
    abstract suspend fun deleteUserNutrientSetting(setting: UserNutritionSettingInformation)
}