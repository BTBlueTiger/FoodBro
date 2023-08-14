package com.bluetiger.foodbrocompose.feature_user.domain.repository

interface OpenFoodFactsRepository {
    suspend fun doNetworkCall()
}