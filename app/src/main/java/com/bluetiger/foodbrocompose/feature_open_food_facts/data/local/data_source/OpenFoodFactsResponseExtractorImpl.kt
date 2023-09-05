package com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source

import android.util.Log
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.repository.OpenFoodFactsResponseExtractor
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ecoscore.EcoScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutrient_levels.NutrientLevels
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriments.Nutriments
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriscore_data.NutriScoreData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general.ProductGeneral
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.DeserializationStrategy
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.net.URL

class OpenFoodFactsResponseExtractorImpl(private val openFoodFactsResponse: OpenFoodFactsResponse) :
    OpenFoodFactsResponseExtractor() {

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

    private suspend fun String.fetchImageUrlToByteArray(): ByteArray {
        return try {
            val url = URL(this)
            val connection = withContext(Dispatchers.IO) {
                url.openConnection()
            }
            withContext(Dispatchers.IO) {
                connection.connect()

                // Input stream to read the image data
                val inputStream = BufferedInputStream(url.openStream())

                // ByteArrayOutputStream to store the image data
                val outputStream = ByteArrayOutputStream()

                // Read the image data into a byte array
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }

                // Close the streams
                inputStream.close()
                outputStream.close()
                // Get the byte array (blob) containing the image data
                outputStream.toByteArray()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ByteArray(0)
        }
    }



    override suspend fun getGeneralProduct(): ProductGeneral {

        return ProductGeneral(
            foodGroups = valueOf("food_groups"),
            brands = valueOf("brands"),
            categories = valueOf("categories"),
            imageByteArray = valueOf("image_url").fetchImageUrlToByteArray(),
            packaging = valueOf("packaging")
        )
    }

    private fun valueOf(valueName: String): String {
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