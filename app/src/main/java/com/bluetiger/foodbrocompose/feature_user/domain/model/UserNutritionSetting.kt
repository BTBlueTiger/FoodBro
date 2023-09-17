package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.room.PrimaryKey

data class UserNutritionSetting(
    @PrimaryKey val name: String,
    val carb: Float,
    val fat: Float,
    val protein: Float,
) : UserInformation {

    enum class ValueType(
        val label: String,
        val memberParam: String
    ) {
        CARB("Carb", "carb"),
        FAT("Fat", "fat"),
        PROTEIN("Protein", "protein")
    }

    enum class PreConfigured(
        val settingsName : String,
        val carb: Int,
        val fat: Int,
        val protein: Int
    ){
        DGE("DGE", 55, 30, 15),
        LOW_CARB("Low Carb", 10, 60, 40),
        HIGH_CARB_LOW_FAT("High Carb Low Fat", 80, 10, 10),
        KETO_DIET("Keto", 5, 65, 30),
        PALEO_DIET("Paleo", 25, 40, 35),
        NONE("", 0, 0,0),
    }

}