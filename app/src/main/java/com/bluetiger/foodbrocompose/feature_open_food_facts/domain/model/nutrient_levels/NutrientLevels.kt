package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutrient_levels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutrientLevels(
    private val fat: String? = null,
    private val salt: String? = null,
    @SerialName("saturated-fat")
    private val saturatedFat: String? = null,
    private val sugars: String? = null,
)