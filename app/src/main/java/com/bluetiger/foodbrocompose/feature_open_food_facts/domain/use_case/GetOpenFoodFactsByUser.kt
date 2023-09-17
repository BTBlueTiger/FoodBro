package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case

import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import kotlinx.coroutines.flow.Flow

class GetOpenFoodFactsByUser(
    private val repository: OpenFoodFactsRepository
) {
    operator fun invoke(userPersonalInformation: UserPersonalInformation): Flow<List<OpenFoodFactsData>> {
        return repository.getOpenFoodFactsResponsesFromUser(userPersonalInformation)
    }
}