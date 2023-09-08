package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ecoscore.EcoScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutrient_levels.NutrientLevels
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriments.Nutriments
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriscore_data.NutriScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general.ProductGeneral
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal

@Entity(
    tableName = "OpenFoodFactsResponses",
    foreignKeys = [
        ForeignKey(
            entity = UserPersonal::class,
            parentColumns = ["name"],
            childColumns = ["userName"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OpenFoodFactsData(
    @PrimaryKey
    val timeStamp: Long = System.currentTimeMillis(),
    val status: Int = 0,
    val barcode: String = "",
    val userName: String = "",
    val nutriScoreData: NutriScoreData? = null,
    val ecoScoreData: EcoScoreData? = null,
    val productGeneral: ProductGeneral? = null,
    val nutriments: Nutriments? = null,
    val nutrientLevels: NutrientLevels? = null,
)