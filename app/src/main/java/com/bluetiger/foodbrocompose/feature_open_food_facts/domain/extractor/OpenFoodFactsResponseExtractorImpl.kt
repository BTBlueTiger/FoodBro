package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.extractor

import android.util.Log
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.EcoScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.NutriScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.NutrientLevels
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.Nutriments
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ProductGeneral
import kotlinx.serialization.DeserializationStrategy

class OpenFoodFactsResponseExtractorImpl(openFoodFactsResponse: OpenFoodFactsResponse) :
    OpenFoodFactsResponseExtractor(openFoodFactsResponse) {

    private fun <T> decode(jsonKey: String, deserializer: DeserializationStrategy<T>): T? {
        if (openFoodFactsResponse.product != null)
            openFoodFactsResponse.product[jsonKey].let { jsonElement ->
                if (jsonElement != null) {
                    return try {
                        jsonConfig.decodeFromJsonElement(deserializer, jsonElement)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                        null
                    }
                }
            }
        return null
    }

    override fun getGeneralProduct(): ProductGeneral? {
        return if (openFoodFactsResponse.product != null)
            ProductGeneral(
                foodGroups = openFoodFactsResponse.valueOf("food_groups"),
                brands = openFoodFactsResponse.valueOf("brands"),
                categories = openFoodFactsResponse.valueOf("categories"),
                imageUrl = openFoodFactsResponse.valueOf("image_url"),
                packaging = openFoodFactsResponse.valueOf("packaging")
            )
        else
            null
    }

    private fun OpenFoodFactsResponse.valueOf(valueName: String): String {
        if(openFoodFactsResponse.product != null) {
            val value = openFoodFactsResponse.product[valueName].toString()
            return value.slice(IntRange(1, value.length - 2))
        }
        return ""
    }

    override fun getNutriments(): Nutriments? =
        decode("nutriments", Nutriments.serializer())

    override fun getNutriScoreData(): NutriScoreData? =
        decode("nutriscore_data", NutriScoreData.serializer())


    override fun getNutrientLevels(): NutrientLevels? =
        decode("nutrient_levels", NutrientLevels.serializer())


    override fun getEcoScoreData(): EcoScoreData? =
        decode("ecoscore_data", EcoScoreData.serializer())

}