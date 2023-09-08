package com.bluetiger.foodbrocompose.feature_user.ui.user_list

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal


sealed class UserListEvents {

    data class SetUser(val userPersonal: UserPersonal) : UserListEvents()

}