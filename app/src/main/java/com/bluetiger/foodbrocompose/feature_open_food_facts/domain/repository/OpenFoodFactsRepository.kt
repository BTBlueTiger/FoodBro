package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository

import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class OpenFoodFactsRepository {

    private val _flowFoodFactResponse = MutableStateFlow(OpenFoodFactsData())
    val flowFoodFactsResponse : StateFlow<OpenFoodFactsData> = _flowFoodFactResponse

    fun setFoodFactsResponse(openFoodFactsData: OpenFoodFactsData){
        _flowFoodFactResponse.value = openFoodFactsData
    }

    abstract fun getResponses(): Flow<List<OpenFoodFactsData>>
    abstract suspend fun getResponseByBarcode(barcode: String): OpenFoodFactsData
    abstract suspend fun insertOpenFoodFactsResponse(response: OpenFoodFactsData)
}