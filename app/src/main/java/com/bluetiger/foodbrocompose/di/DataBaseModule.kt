package com.bluetiger.foodbrocompose.di

import android.app.Application
import androidx.room.Room
import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideFoodBroDataBase(app: Application): FoodBroDataBase {
        return Room.databaseBuilder(
            app,
            FoodBroDataBase::class.java,
            FoodBroDataBase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

}