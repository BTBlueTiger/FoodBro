package com.bluetiger.foodbrocompose.data.repository

import android.app.Application
import com.bluetiger.foodbrocompose.data.remote.OpenFoodFactsApi
import com.bluetiger.foodbrocompose.feature_user.domain.repository.OpenFoodFactsRepository
import javax.inject.Inject

class OpenOpenFoodFactsRepositoryImpl @Inject constructor(
    private val api: OpenFoodFactsApi,
    private val appContext: Application
): OpenFoodFactsRepository {

    override suspend fun doNetworkCall() {

    }

}