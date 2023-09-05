package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository

import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class OpenFoodFactsRepository {

    private val _flowFoodFactResponse = MutableStateFlow(OpenFoodFactsData())
    val flowFoodFactsResponse : StateFlow<OpenFoodFactsData> = _flowFoodFactResponse

    fun setFoodFactsResponse(openFoodFactsData: OpenFoodFactsData){
        _flowFoodFactResponse.value = openFoodFactsData
    }

    abstract fun getOpenFoodFactsResponses(): Flow<List<OpenFoodFactsData>>
    abstract fun getOpenFoodFactsResponsesFromUser(user: User): Flow<List<OpenFoodFactsData>>
    abstract suspend fun getOpenFoodFactResponseByTimeStamp(timestamp: Long): OpenFoodFactsData
    abstract suspend fun insertOpenFoodFacts(response: OpenFoodFactsData)
}