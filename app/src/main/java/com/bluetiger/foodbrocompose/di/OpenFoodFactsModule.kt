package com.bluetiger.foodbrocompose.di

import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.repository.OpenFoodFactsRepositoryImpl
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.GetLastOpenFoodFactData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.GetOpenFoodFactByTimeStamp
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.GetOpenFoodFactsByUser
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.InsertOpenFoodFact
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.OpenFoodFactDataUseCases
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.SetLastOpenFoodFactData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenFoodFactsModule {
    @Provides
    @Singleton
    fun provideOpenFoodFactsRepository(db: FoodBroDataBase) : OpenFoodFactsRepository {
        return OpenFoodFactsRepositoryImpl(db.foodFactsDao)
    }

    @Provides
    @Singleton
    fun provideOpenFoodFactsUseCases(repository: OpenFoodFactsRepository) : OpenFoodFactDataUseCases {
        return OpenFoodFactDataUseCases(
            getLastOpenFoodFactData = GetLastOpenFoodFactData(repository),
            getOpenFoodFactByTimeStamp = GetOpenFoodFactByTimeStamp(repository),
            getOpenFoodFactsByUser = GetOpenFoodFactsByUser(repository),
            insertOpenFoodFact = InsertOpenFoodFact(repository),
            setLastOpenFoodFactData = SetLastOpenFoodFactData(repository)
        )
    }
}