package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalInformationRepository

class DeleteUserPersonalInformation(
    private val repository: UserPersonalInformationRepository
) {
    suspend operator fun invoke(userPersonalInformation: UserPersonalInformation){
        repository.deleteUser(userPersonalInformation)
    }
}