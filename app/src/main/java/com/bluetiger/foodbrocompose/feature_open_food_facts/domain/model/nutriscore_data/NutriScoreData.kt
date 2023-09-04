package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriscore_data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
@Serializable
data class NutriScoreData(
    val energy: Int = 0,
    @SerialName("energy_points")
    val energyPoints: Int = 0,
    @SerialName("energy_value")
    val energyValue: Int = 0,
    val fiber: Int = 0,
    @SerialName("fiber_points")
    val fiberPoints: Int = 0,
    @SerialName("fiber_value")
    val fiberValue: Int = 0,
    @SerialName("fruits_vegetables_nuts_colza_walnut_olive_oils")
    val fruitsVegetablesNutsColzaWalnutOliveOils: Int = 0,
    @SerialName("fruits_vegetables_nuts_colza_walnut_olive_oils_points")
    val fruitsVegetablesNutsColzaWalnutOliveOilsPoints: Int = 0,
    @SerialName("fruits_vegetables_nuts_colza_walnut_olive_oils_value")
    val fruitsVegetablesNutsColzaWalnutOliveOilsValue: Int = 0,
    val grade: String = "",
    @SerialName("is_beverage")
    val isBeverage: Int = 0,
    @SerialName("is_cheese")
    val isCheese: Int = 0,
    @SerialName("is_fat")
    val isFat: Int = 0,
    @SerialName("is_water")
    val isWater: Int = 0,
    @SerialName("negative_points")
    val negativePoints: Int = 0,
    @SerialName("positive_points")
    val positivePoints: Int = 0,
    val proteins: Float = 0.0f,
    @SerialName("proteins_points")
    val proteinsPoints: Int = 0,
    @SerialName("proteins_value")
    val proteinsValue: Float = 0.0f,
    @SerialName("saturated_fat")
    val saturatedFat: Float = 0.0f,
    @SerialName("saturated_fat_points")
    val saturatedFatPoints: Int = 0,
    @SerialName("saturated_fat_ratio")
    val saturatedFatRatio: Float = 0.0f,
    @SerialName("saturated_fat_ratio_points")
    val saturatedFatRatioPoints: Int = 0,
    @SerialName("saturated_fat_ratio_value")
    val saturatedFatRatioValue: Float = 0.0f,
    @SerialName("saturated_fat_value")
    val saturatedFatValue: Float = 0.0f,
    val score: Int = 0,
    val sodium: Float = 0.0f,
    @SerialName("sodium_points")
    val sodiumPoints: Int = 0,
    @SerialName("sodium_value")
    val sodiumValue: Float = 0.0f,
    val sugars: Float = 0.0f,
    @SerialName("sugars_points")
    val sugarsPoints: Int = 0,
    @SerialName("sugars_value")
    val sugarsValue: Float = 0.0f
)

