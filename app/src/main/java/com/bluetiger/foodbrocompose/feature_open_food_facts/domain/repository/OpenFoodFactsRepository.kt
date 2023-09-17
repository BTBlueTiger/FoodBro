package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository

import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class OpenFoodFactsRepository {

    private val _flowFoodFactResponse = MutableStateFlow(OpenFoodFactsData())
    val flowFoodFactsResponse : StateFlow<OpenFoodFactsData> = _flowFoodFactResponse

    fun setLastOpenFoodFactsResponseData(openFoodFactsData: OpenFoodFactsData){
        _flowFoodFactResponse.value = openFoodFactsData
    }
    abstract fun getOpenFoodFactsResponsesFromUser(userPersonalInformation: UserPersonalInformation): Flow<List<OpenFoodFactsData>>
    abstract suspend fun getOpenFoodFactResponseByTimeStamp(timestamp: Long): OpenFoodFactsData
    abstract suspend fun insertOpenFoodFact(response: OpenFoodFactsData)
}