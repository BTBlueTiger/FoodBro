package com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote

import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface OpenFoodFactsService {

    suspend fun getFoodFactsByBarcode(barcode: String): OpenFoodFactsResponse?
    companion object {

        const val TAG = "OpenFoodFactsService"
        fun create(): OpenFoodFactsService {
            return OpenFoodFactsServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            json = kotlinx.serialization.json.Json {
                                isLenient = true
                                ignoreUnknownKeys = true
                            })
                    }
                })
        }
    }
}