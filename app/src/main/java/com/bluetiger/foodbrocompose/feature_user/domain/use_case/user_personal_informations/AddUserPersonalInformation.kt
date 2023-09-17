package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations

import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalInformationRepository

class AddUserPersonalInformation(
    private val repository: UserPersonalInformationRepository
) {

    @Throws(UserPersonalInformation.InvalidUserException::class)
    suspend operator fun invoke(userPersonalInformation: UserPersonalInformation) {
        if(userPersonalInformation.name.isBlank()){
            throw UserPersonalInformation.InvalidUserException("The email of a user can't be empty.")
        }
        if(userPersonalInformation.birthday == 0L){
            throw UserPersonalInformation.InvalidUserException("The birthday of a user can't be empty")
        }
        if(userPersonalInformation.height >= 250 || userPersonalInformation.height <= 0){
            throw UserPersonalInformation.InvalidUserException("The Height is not in between 0 - 250")
        }
        if(userPersonalInformation.weight >= 500 || userPersonalInformation.weight <= 0){
            throw UserPersonalInformation.InvalidUserException("The weight is not in between 0 - 500")
        }
        if(userPersonalInformation.gender == Gender.NONE){
            throw UserPersonalInformation.InvalidUserException("The gender of the user has to be set")
        }
        repository.insertUser(userPersonalInformation)
    }

}