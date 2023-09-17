package com.bluetiger.foodbrocompose.feature_user.domain.repository

import com.bluetiger.foodbrocompose.common.FoodBroRepository
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation

abstract class UserActivityInformationRepository : FoodBroRepository {

    abstract suspend fun getUserActivityLevels(userPersonalInformation: UserPersonalInformation) : UserActivityInformation
    abstract suspend fun insertActivityLevel(activity: UserActivityInformation)
    abstract suspend fun deleteUserActivity(activity: UserActivityInformation)

}