package com.bluetiger.foodbrocompose.feature_user.ui.user_list

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation


sealed class UserListEvents {

    data class SetUser(val userPersonalInformation: UserPersonalInformation) : UserListEvents()

}