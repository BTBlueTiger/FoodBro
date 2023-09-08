package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal

import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalFoodBroRepository

class AddUser(
    private val repository: UserPersonalFoodBroRepository
) {

    @Throws(UserPersonal.InvalidUserException::class)
    suspend operator fun invoke(userPersonal: UserPersonal) {
        if(userPersonal.name.isBlank()){
            throw UserPersonal.InvalidUserException("The email of a user can't be empty.")
        }
        if(userPersonal.birthday == 0L){
            throw UserPersonal.InvalidUserException("The birthday of a user can't be empty")
        }
        if(userPersonal.height >= 250 || userPersonal.height <= 0){
            throw UserPersonal.InvalidUserException("The Height is not in between 0 - 250")
        }
        if(userPersonal.weight >= 500 || userPersonal.weight <= 0){
            throw UserPersonal.InvalidUserException("The weight is not in between 0 - 500")
        }
        if(userPersonal.gender == Gender.NONE){
            throw UserPersonal.InvalidUserException("The gender of the user has to be set")
        }
        repository.insertUser(userPersonal)
    }

}