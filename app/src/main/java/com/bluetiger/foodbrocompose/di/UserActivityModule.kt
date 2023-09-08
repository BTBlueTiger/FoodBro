package com.bluetiger.foodbrocompose.di

import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import com.bluetiger.foodbrocompose.feature_user.data.repository.UserActivityFoodBroRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserActivityFoodBroRepository
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity.AddUserActivity
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity.DeleteUserActivity
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity.GetUserActivity
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity.UserActivityUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserActivityModule {

    @Provides
    @Singleton
    fun provideUserActivityRepository(db: FoodBroDataBase) : UserActivityFoodBroRepository{
        return UserActivityFoodBroRepositoryImpl(db.userActivityDao)
    }

    @Provides
    @Singleton
    fun provideUserActivityUseCases(repository: UserActivityFoodBroRepository) = UserActivityUseCases(
        getUserActivity = GetUserActivity(repository),
        addUserActivity = AddUserActivity(repository),
        deleteUserActivity = DeleteUserActivity(repository)
    )
}