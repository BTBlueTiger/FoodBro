package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserActivityInformationRepository

class AddUserActivityInformation(
    private val repository: UserActivityInformationRepository
) {
    suspend operator fun invoke(userActivityInformation: UserActivityInformation){
        repository.insertActivityLevel(userActivityInformation)
    }
}