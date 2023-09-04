package com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote

import android.util.Log
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class OpenFoodFactsServiceImpl(
    private val client: HttpClient
) : OpenFoodFactsService {

    override suspend fun getFoodFactsByBarcode(barcode: String): OpenFoodFactsResponse {
        return try {
            client.get {
                url(OpenFoodFactsRoutes.Barcode(barcode).getUrlBuilder())
            }
        } catch (e: Exception) {
            Log.e(OpenFoodFactsService.TAG, e.message.toString())
            OpenFoodFactsResponse(
                barcode,
                null,
                0
            )
        }
    }
}