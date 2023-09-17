package com.bluetiger.foodbrocompose.di

import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import com.bluetiger.foodbrocompose.feature_user.data.repository.UserActivityInformationRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user.data.repository.UserFlowRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user.data.repository.UserPersonalPersonalInformationRepositoryImpl
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserActivityInformationRepository
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserFlowRepository
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalInformationRepository
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations.AddUserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations.DeleteUserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations.GetUserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations.UserActivityInformationUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations.CurrentUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations.NewUser
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations.UserFlowUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_flow_informations.UserIsSet
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.AddUserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.DeleteUserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.GetAllLocalUserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.GetPendingNewUserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.GetUserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.InitPendingNewUserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.SetPendingNewUserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.UserPersonalInformationUseCases
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
    fun provideUserUseCases(
        userFlowRepository: UserFlowRepository,
        userPersonalInformationRepository: UserPersonalInformationRepository,
        userActivityInformationRepository: UserActivityInformationRepository
    )  =
        UserUseCases(
            pendingInformation = provideUserFlowUseCases(userFlowRepository),
            personalInformation = provideUserPersonalUseCases(userPersonalInformationRepository),
            activityInformation = provideUserActivityUseCases(userActivityInformationRepository)
        )

    @Provides
    @Singleton
    fun provideUserFlowUseCases(repository: UserFlowRepository) : UserFlowUseCases = UserFlowUseCases(
        currentUser = CurrentUser(repository),
        newUser = NewUser(repository),
        userIsSet = UserIsSet(repository)
    )

    @Provides
    @Singleton
    fun provideUserFlowRepository() : UserFlowRepository = UserFlowRepositoryImpl()


    /**
     * PersonalInformation
     */
    @Provides
    @Singleton
    fun provideUserPersonalRepository(db: FoodBroDataBase) : UserPersonalInformationRepository {
        return UserPersonalPersonalInformationRepositoryImpl(db.userPersonalInformationDao)
    }

    @Provides
    @Singleton
    fun provideUserPersonalUseCases(repository: UserPersonalInformationRepository) = UserPersonalInformationUseCases(
        getAllLocalUserPersonalInformation = GetAllLocalUserPersonalInformation(repository),
        deleteUserPersonalInformation = DeleteUserPersonalInformation(repository),
        addUserPersonalInformation = AddUserPersonalInformation(repository),
        getUserPersonalInformation = GetUserPersonalInformation(repository),
        getPendingNewUserPersonalInformation = GetPendingNewUserPersonalInformation(repository),
        setPendingNewUserPersonalInformation = SetPendingNewUserPersonalInformation(repository),
        initPendingNewUserPersonalInformation = InitPendingNewUserPersonalInformation(repository)
    )


    /**
     * ActivityInformation
     */
    @Provides
    @Singleton
    fun provideUserActivityRepository(db: FoodBroDataBase) : UserActivityInformationRepository {
        return UserActivityInformationRepositoryImpl(db.userActivityInformationDao)
    }

    @Provides
    @Singleton
    fun provideUserActivityUseCases(repository: UserActivityInformationRepository) = UserActivityInformationUseCases(
        getUserActivityInformation = GetUserActivityInformation(repository),
        addUserActivityInformation = AddUserActivityInformation(repository),
        deleteUserActivityInformation = DeleteUserActivityInformation(repository)
    )
}