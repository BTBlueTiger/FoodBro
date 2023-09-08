package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal

data class UserUseCases(
    val addUser: AddUser,
    val deleteUser: DeleteUser,
    val getUser: GetUser,
    val getUsers: GetUsers,
    val getCurrentUser: GetCurrentUser,
    val setCurrentUser: SetCurrentUser
)
