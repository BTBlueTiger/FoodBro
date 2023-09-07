package com.bluetiger.foodbrocompose.di

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
object PermissionModule {
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

}