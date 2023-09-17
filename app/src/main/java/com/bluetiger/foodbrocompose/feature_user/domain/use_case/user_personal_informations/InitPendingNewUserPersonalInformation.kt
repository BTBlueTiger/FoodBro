package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations

import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalInformationRepository

class InitPendingNewUserPersonalInformation(
    private val repository: UserPersonalInformationRepository
) {
    operator fun invoke(){
        repository.initPendingNewUserPersonalInformation()
    }
}