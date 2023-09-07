package com.bluetiger.foodbrocompose.di

import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import com.bluetiger.foodbrocompose.feature_user_list.data.repository.FoodBroListRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user_list.domain.repository.FoodBroListRepository
import com.bluetiger.foodbrocompose.feature_user_list.domain.use_case.AddToFavoriteList
import com.bluetiger.foodbrocompose.feature_user_list.domain.use_case.FoodBroListUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserListModule {
    @Provides
    @Singleton
    fun provideFoodBroListRepository(db: FoodBroDataBase) : FoodBroListRepository{
        return FoodBroListRepositoryImpl(db.foodBroListDao)
    }

    @Provides
    @Singleton
    fun provideFoodBroListUseCases(repository: FoodBroListRepository) = FoodBroListUseCases(
        addToFavoriteList = AddToFavoriteList(repository)
    )

}