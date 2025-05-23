package com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.repository

import android.util.Log
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source.OpenFoodFactsResponseExtractorImpl
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ecoscore.EcoScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutrient_levels.NutrientLevels
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriments.Nutriments
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriscore_data.NutriScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general.ProductGeneral
import kotlinx.serialization.json.Json

abstract class OpenFoodFactsResponseExtractor {
    abstract fun getNutriments(): Nutriments?
    abstract suspend fun getGeneralProduct(): ProductGeneral?
    abstract fun getNutriScoreData(): NutriScoreData?
    abstract fun getNutrientLevels(): NutrientLevels?
    abstract fun getEcoScoreData(): EcoScoreData?

    protected val TAG = "OpenFoodFactsResponseExtractor"

    val jsonConfig = Json { ignoreUnknownKeys = true }

    companion object {
        suspend fun createData(openFoodFactsResponse: OpenFoodFactsResponse): OpenFoodFactsData {
            if (openFoodFactsResponse.status == 0) {
                return OpenFoodFactsData(status = 0, barcode = openFoodFactsResponse.code)
            }
            val extractor = OpenFoodFactsResponseExtractorImpl(openFoodFactsResponse)
            return OpenFoodFactsData(
                barcode = openFoodFactsResponse.code,
                status = 1,
                nutrientLevels = extractor.getNutrientLevels(),
                ecoScoreData = extractor.getEcoScoreData(),
                nutriments = extractor.getNutriments(),
                nutriScoreData = extractor.getNutriScoreData(),
                productGeneral = extractor.getGeneralProduct()
            )
        }
    }

}