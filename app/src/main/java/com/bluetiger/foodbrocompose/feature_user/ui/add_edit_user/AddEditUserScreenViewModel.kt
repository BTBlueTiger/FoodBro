package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations.UserActivityInformationUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations.UserPersonalInformationUseCases
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.AddEditUserTab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditUserScreenViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {


    val availableTabs = listOf(
        AddEditUserTab.PersonalInformation,
        AddEditUserTab.ActivityInformation,
        AddEditUserTab.FoodNutritionSettings
    )

    private val _selectedTab = mutableIntStateOf(0)
    val selectedTab: State<Int> = _selectedTab


    fun onEvent(event: AddEditUserEvent) {
        when (event) {
            is AddEditUserEvent.ChangeTab -> {
                if (userUseCases.pendingInformation.newUser.personalInformationIsSet.value!!)
                    _selectedTab.intValue = event.tabIndex
            }
            is AddEditUserEvent.SaveUser -> {
                val newUser = userUseCases.pendingInformation.newUser.getValue()
                val personal = newUser[UserPersonalInformation::class] as UserPersonalInformation
                var activities = newUser[UserActivityInformation::class] as UserActivityInformation
                var nutrition = newUser[UserNutritionSettingInformation::class] as UserNutritionSettingInformation
                activities = activities.copy(userName = personal.name)
                nutrition = nutrition.copy(userName = personal.name)
                viewModelScope.launch {
                    userUseCases.personalInformation.addUserPersonalInformation(personal)
                    userUseCases.activityInformation.addUserActivityInformation(activities)
                    userUseCases.nutritionSettingInformation.addUserNutritionSettingInformation(nutrition)
                    userUseCases.pendingInformation.newUser.clear()
                }
            }
        }
    }

}