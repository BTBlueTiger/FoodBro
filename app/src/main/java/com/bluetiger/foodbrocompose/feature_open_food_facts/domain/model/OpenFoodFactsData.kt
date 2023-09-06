package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ecoscore.EcoScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutrient_levels.NutrientLevels
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriments.Nutriments
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriscore_data.NutriScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general.ProductGeneral

@Entity(tableName = "OpenFoodFactsResponses")
data class OpenFoodFactsData(
    val status: Int = 0,
    val barcode: String = "",
    @PrimaryKey
    val timeStamp: Long = System.currentTimeMillis(),
    val userMail: String = "",
    val nutriScoreData: NutriScoreData? = null,
    val ecoScoreData: EcoScoreData? = null,
    val productGeneral: ProductGeneral? = null,
    val nutriments: Nutriments? = null,
    val nutrientLevels: NutrientLevels? = null,
)