package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalInformationRepository
import kotlinx.coroutines.flow.Flow

class GetAllLocalUserPersonalInformation(
    private val repository: UserPersonalInformationRepository
) {
    operator fun invoke() : Flow<List<UserPersonalInformation>> = repository.getUsers()

}