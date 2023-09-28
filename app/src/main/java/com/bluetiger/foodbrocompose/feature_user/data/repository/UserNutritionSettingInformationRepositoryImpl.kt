package com.bluetiger.foodbrocompose.feature_user.data.repository

import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserNutrientSettingInformationDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserNutritionSettingInformationRepository

class UserNutritionSettingInformationRepositoryImpl(
    private val dao: UserNutrientSettingInformationDao
) : UserNutritionSettingInformationRepository() {
    override suspend fun getUserNutrientInformation(userPersonalInformation: UserPersonalInformation): UserNutritionSettingInformation {
        return dao.getUserNutrientSettingInformation(userPersonalInformation.name)
    }

    override suspend fun insertUserNutrientSetting(setting: UserNutritionSettingInformation) {
        dao.insert(setting)
    }

    override suspend fun deleteUserNutrientSetting(setting: UserNutritionSettingInformation) {
        dao.delete(setting)
    }
}