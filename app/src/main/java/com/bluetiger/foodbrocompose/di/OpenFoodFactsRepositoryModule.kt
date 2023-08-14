package com.bluetiger.foodbrocompose.di

import android.app.Application
import com.bluetiger.foodbrocompose.data.remote.OpenFoodFactsApi
import com.bluetiger.foodbrocompose.data.repository.OpenOpenFoodFactsRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user.domain.repository.OpenFoodFactsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenFoodFactsRepositoryModule {
    @Provides
    @Singleton
    fun provideOpenFoodFactsApi(): OpenFoodFactsApi {
        return Retrofit.Builder()
            .baseUrl("https://world.openfoodfacts.org/")
            .build()
            .create(OpenFoodFactsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenFoodFactsRepository(
        openFoodFactsApi: OpenFoodFactsApi,
        app: Application
    ): OpenOpenFoodFactsRepositoryImpl =
        OpenOpenFoodFactsRepositoryImpl(openFoodFactsApi, app)
}