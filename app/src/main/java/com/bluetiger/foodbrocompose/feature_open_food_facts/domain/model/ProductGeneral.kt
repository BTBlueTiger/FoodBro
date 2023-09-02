package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ProductGeneral(
    @SerialName("image_url")
    val imageUrl: String,
    val brands: String,
    val categories: String,
    @SerialName("food_groups")
    val foodGroups: String,
    val packaging: String,
)
