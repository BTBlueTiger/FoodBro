package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository

import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import kotlinx.coroutines.flow.Flow

interface FoodFactsRepository {
    fun getResponses(): Flow<List<OpenFoodFactsResponse>>
    fun getResponseByBarcode(barcode: String): OpenFoodFactsResponse
    fun insertOpenFoodFactsResponse(response: OpenFoodFactsResponse)
}