package com.bluetiger.foodbrocompose.di

import android.app.Application
import androidx.room.Room
import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.repository.OpenFoodFactsRepositoryImpl
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import com.bluetiger.foodbrocompose.feature_user.data.repository.UserRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.AddUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.DeleteUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.GetUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.GetUsers
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.permission.data.repository.PermissionRepository
import com.bluetiger.foodbrocompose.permission.repository.PermissionRepositoryImpl
import com.bluetiger.foodbrocompose.permission.use_case.PermissionUseCase
import com.bluetiger.foodbrocompose.permission.use_case.PermissionUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    @Singleton
    fun provideOpenFoodFactsRepository(db: FoodBroDataBase) : OpenFoodFactsRepository {
        return OpenFoodFactsRepositoryImpl(db.foodFactsDao)
    }

    @Provides
    @Singleton
    fun providePermissionRepository() : PermissionRepository {
        return PermissionRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePermissionUseCases(repository: PermissionRepository) = PermissionUseCases(
        camara = PermissionUseCase.Camara(repository)
    )

    @Provides
    @Singleton
    fun provideUserRepository(db: FoodBroDataBase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository) = UserUseCases(
        getUsers = GetUsers(repository),
        deleteUser = DeleteUser(repository),
        addUser = AddUser(repository),
        getUser = GetUser(repository)
    )

}