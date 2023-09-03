package com.bluetiger.foodbrocompose.feature_user.ui.user_list

import com.bluetiger.foodbrocompose.feature_user.domain.model.User


sealed class UserListEvents {

    data class SetUser(val user: User) : UserListEvents()

}