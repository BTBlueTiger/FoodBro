package com.bluetiger.foodbrocompose.data.remote

import retrofit2.http.GET

interface OpenFoodFactsApi {
    @GET("test")
    suspend fun doNetworkCall()
}