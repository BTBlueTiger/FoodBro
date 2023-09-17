package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.nutrition_settings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSetting
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Constructor
import javax.inject.Inject

@HiltViewModel
class AddEditUserNutritionSettingsViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val pendingMap by lazy { userUseCases.pendingInformation.newUser.getValue() }
    private val newUserNutritionSetting by lazy { pendingMap[UserNutritionSetting::class] as UserNutritionSetting }

    private val preconfiguredList = UserNutritionSetting.PreConfigured.values()

    private val _preConfigured = mutableStateOf(UserNutritionSetting.PreConfigured.NONE)
    val preConfigured : State<UserNutritionSetting.PreConfigured> = _preConfigured

    private val _macros = UserNutritionSetting.ValueType.values().map {
        it to 0f
    }.toMutableStateMap()

    
}