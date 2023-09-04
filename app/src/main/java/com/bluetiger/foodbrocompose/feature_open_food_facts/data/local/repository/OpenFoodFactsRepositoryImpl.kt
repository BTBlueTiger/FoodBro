package com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.repository

import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source.OpenFoodFactsDao
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OpenFoodFactsRepositoryImpl(
    private val dao: OpenFoodFactsDao
) : OpenFoodFactsRepository() {

    private val _lastResponseState = MutableStateFlow(OpenFoodFactsData(0))
    val lastResponse: StateFlow<OpenFoodFactsData> = _lastResponseState

    override fun getResponses(): Flow<List<OpenFoodFactsData>> {
        return dao.getAllFoodFactsResponses()
    }

    override suspend fun getResponseByBarcode(barcode: String): OpenFoodFactsData {
        return dao.getOpenFoodFactsByBarcode(barcode)
    }

    override suspend fun insertOpenFoodFactsResponse(response: OpenFoodFactsData) {
        _lastResponseState.value = response
        dao.insert(response)
    }

}