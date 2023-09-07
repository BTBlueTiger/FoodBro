package com.bluetiger.foodbrocompose.di

import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import com.bluetiger.foodbrocompose.feature_user.data.repository.UserFoodBroRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFoodBroRepository
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.AddUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.DeleteUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.GetCurrentUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.GetUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.GetUsers
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.SetCurrentUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideUserRepository(db: FoodBroDataBase) : UserFoodBroRepository {
        return UserFoodBroRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserFoodBroRepository) = UserUseCases(
        getUsers = GetUsers(repository),
        deleteUser = DeleteUser(repository),
        addUser = AddUser(repository),
        getUser = GetUser(repository),
        getCurrentUser = GetCurrentUser(repository),
        setCurrentUser = SetCurrentUser(repository)
    )

}