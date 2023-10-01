package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_nutrition_setting",
    foreignKeys = [
        ForeignKey(
            entity = UserPersonalInformation::class,
            parentColumns = ["name"],
            childColumns = ["userName"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserNutritionSettingInformation(
    @PrimaryKey
    val userNutritionSettingId: Long = 0,
    val userName: String = "",
    val carb: Int = 55,
    val fat: Int = 30,
    val protein: Int = 15,
    @ColumnInfo("option_name") val optionName : String = "DGE"
) : UserInformation {

    enum class ValueType(
        val label: String,
        val memberParam: String
    ) {
        CARB("Carb", "carb"),
        FAT("Fat", "fat"),
        PROTEIN("Protein", "protein")
    }

    enum class Option(
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
        Custom("Custom", 0, 0,0),
    }

    operator fun iterator(): Iterator<Pair<ValueType, Int>> =
        listOf(
            Pair(ValueType.CARB, carb),
            Pair(ValueType.FAT, fat),
            Pair(ValueType.PROTEIN, protein)
        ).iterator()

}