package com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.repository

import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source.OpenFoodFactsDao
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OpenFoodFactsRepositoryImpl(
    private val dao: OpenFoodFactsDao
) : OpenFoodFactsRepository() {

    private val _lastResponseState = MutableStateFlow(OpenFoodFactsData(0))
    val lastResponse: StateFlow<OpenFoodFactsData> = _lastResponseState


    override fun getOpenFoodFactsResponsesFromUser(userPersonalInformation: UserPersonalInformation): Flow<List<OpenFoodFactsData>> {
        return dao.getOpenFoodFactResponsesByUser(userName = userPersonalInformation.name)
    }

    override suspend fun getOpenFoodFactResponseByTimeStamp(timestamp: Long): OpenFoodFactsData {
        return dao.getOpenFoodFactsByTimeStamp(timestamp)
    }
    override suspend fun insertOpenFoodFact(response: OpenFoodFactsData) {
        _lastResponseState.value = response
        dao.insert(response)
    }

}