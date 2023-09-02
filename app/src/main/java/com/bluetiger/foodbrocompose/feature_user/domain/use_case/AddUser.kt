package com.bluetiger.foodbrocompose.feature_user.domain.use_case

import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository

class AddUser(
    private val repository: UserRepository
) {

    @Throws(User.InvalidUserException::class)
    suspend operator fun invoke(user: User) {
        if(user.email.isBlank()){
            throw User.InvalidUserException("The email of a user can't be empty.")
        }
        if(user.birthday == 0L){
            throw User.InvalidUserException("The birthday of a user can't be empty")
        }
        if(user.height >= 250 || user.height <= 0){
            throw User.InvalidUserException("The Height is not in between 0 - 250")
        }
        if(user.weight >= 500 || user.weight <= 0){
            throw User.InvalidUserException("The weight is not in between 0 - 500")
        }
        if(user.gender == Gender.NONE){
            throw User.InvalidUserException("The gender of the user has to be set")
        }
        repository.insertUser(user)
    }

}