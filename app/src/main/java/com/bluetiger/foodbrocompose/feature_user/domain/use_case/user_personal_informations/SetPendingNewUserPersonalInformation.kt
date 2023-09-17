package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalInformationRepository


class SetPendingNewUserPersonalInformation(
    private val repository: UserPersonalInformationRepository
) {
    operator fun <T : Any> invoke(valueType: UserPersonalInformation.ValueType, newValue : T){
        repository.setPendingNewUserPersonalInformation(valueType, newValue)
    }
}