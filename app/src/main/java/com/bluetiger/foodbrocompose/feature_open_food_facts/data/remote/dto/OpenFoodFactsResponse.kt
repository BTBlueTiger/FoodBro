package com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class OpenFoodFactsResponse(
    val code : String,
    val product: JsonObject? = null,
    val status: Int
)