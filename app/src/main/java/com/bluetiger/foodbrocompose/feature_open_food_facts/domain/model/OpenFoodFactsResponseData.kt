package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model

import androidx.room.Entity

@Entity(tableName = "OpenFoodFactsResponses")
data class OpenFoodFactsResponseData(
    val timeStamp: Long,
    val barcode: String,
    val status: Int,
)