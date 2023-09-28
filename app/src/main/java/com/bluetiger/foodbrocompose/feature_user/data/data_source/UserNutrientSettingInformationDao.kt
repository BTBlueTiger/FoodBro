package com.bluetiger.foodbrocompose.feature_user.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.bluetiger.foodbrocompose.database.room.BaseDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation

@Dao
interface UserNutrientSettingInformationDao : BaseDao<UserNutritionSettingInformation> {
    @Query("SELECT * FROM user_nutrition_setting WHERE userName = :userPersonalName")
    suspend fun getUserNutrientSettingInformation(userPersonalName: String): UserNutritionSettingInformation
}