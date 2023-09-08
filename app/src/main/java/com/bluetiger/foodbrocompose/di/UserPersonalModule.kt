package com.bluetiger.foodbrocompose.di

import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import com.bluetiger.foodbrocompose.feature_user.data.repository.UserPersonalPersonalFoodBroRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalFoodBroRepository
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal.AddUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal.DeleteUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal.GetCurrentUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal.GetUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal.GetUsers
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal.SetCurrentUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserPersonalModule {
    @Provides
    @Singleton
    fun provideUserRepository(db: FoodBroDataBase) : UserPersonalFoodBroRepository {
        return UserPersonalPersonalFoodBroRepositoryImpl(db.userPersonalDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserPersonalFoodBroRepository) = UserUseCases(
        getUsers = GetUsers(repository),
        deleteUser = DeleteUser(repository),
        addUser = AddUser(repository),
        getUser = GetUser(repository),
        getCurrentUser = GetCurrentUser(repository),
        setCurrentUser = SetCurrentUser(repository)
    )

}