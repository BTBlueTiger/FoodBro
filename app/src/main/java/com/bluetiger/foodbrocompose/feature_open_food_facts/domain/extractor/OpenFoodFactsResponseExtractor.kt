package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.extractor

import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.EcoScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.NutriScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.NutrientLevels
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.Nutriments
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ProductGeneral
import kotlinx.serialization.json.Json

abstract class OpenFoodFactsResponseExtractor(protected val openFoodFactsResponse: OpenFoodFactsResponse) {
    abstract fun getNutriments(): Nutriments?
    abstract fun getGeneralProduct(): ProductGeneral?
    abstract fun getNutriScoreData(): NutriScoreData?
    abstract fun getNutrientLevels(): NutrientLevels?
    abstract fun getEcoScoreData(): EcoScoreData?

    protected val TAG = "OpenFoodFactsResponseExtractor"

    val jsonConfig = Json { ignoreUnknownKeys = true }

    operator fun invoke(openFoodFactsResponse: OpenFoodFactsResponse) : OpenFoodFactsResponseExtractorImpl {
        return OpenFoodFactsResponseExtractorImpl(openFoodFactsResponse)
    }

}