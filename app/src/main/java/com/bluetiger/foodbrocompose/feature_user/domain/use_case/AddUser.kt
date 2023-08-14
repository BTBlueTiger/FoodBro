package com.bluetiger.foodbrocompose.feature_user.domain.use_case

import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.InvalidUserException
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository

class AddUser(
    private val repository: UserRepository
) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user: User) {
        if(user.email.isBlank()){
            throw InvalidUserException("The email of a user can't be empty.")
        }
        if(user.birthday == 0L){
            throw InvalidUserException("The birthday of a user can't be empty")
        }
        if(user.height >= 250 || user.height <= 0){
            throw InvalidUserException("The Height is not in between 0 - 250")
        }
        if(user.weight >= 500 || user.weight <= 0){
            throw InvalidUserException("The weight is not in between 0 - 500")
        }
        if(user.gender == Gender.NONE){
            throw InvalidUserException("The gender of the user has to be set")
        }
        repository.insertUser(user)
    }

}