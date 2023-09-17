package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.AddEditUserTab
import kotlin.reflect.KClass

sealed class AddEditUserEvent {
    object SaveUser : AddEditUserEvent()

    object InitNewUser : AddEditUserEvent()

}
