package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserActivityInformationRepository

class GetUserActivityInformation(
    private val repository: UserActivityInformationRepository
) {
    suspend operator fun invoke(user: UserPersonalInformation) : UserActivityInformation{
        return repository.getUserActivityLevels(userPersonalInformation = user)
    }
}