package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case

import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository

class SetLastOpenFoodFactData(
    private val repository: OpenFoodFactsRepository
) {
    operator fun invoke(openFoodFactsData: OpenFoodFactsData){
        repository.setLastOpenFoodFactsResponseData(openFoodFactsData)
    }
}