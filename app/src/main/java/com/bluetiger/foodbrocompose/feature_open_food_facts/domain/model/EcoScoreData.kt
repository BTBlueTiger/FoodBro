package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class EcoScoreData(
    val grade: String,
    val score: Int,
)
