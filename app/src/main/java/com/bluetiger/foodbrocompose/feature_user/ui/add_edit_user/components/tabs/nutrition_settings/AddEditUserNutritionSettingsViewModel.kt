package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.nutrition_settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserNutritionSettingsViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val pendingMap by lazy { userUseCases.pendingInformation.newUser.getValue() }
    private val newUserNutritionSettingInformation by lazy { pendingMap[UserNutritionSettingInformation::class] as UserNutritionSettingInformation }

    private val _selectedOption = mutableStateOf(UserNutritionSettingInformation.Option.values().findLast { newUserNutritionSettingInformation.optionName == it.settingsName }!!)
    val selectedOption : State<UserNutritionSettingInformation.Option> = _selectedOption


    private val _macros = UserNutritionSettingInformation.ValueType.values().map {
        it to 0
    }.toMutableStateMap()

    val macros : SnapshotStateMap<UserNutritionSettingInformation.ValueType, Int> = _macros

    fun onEvent(event: AddEditUserNutritionSettingsEvent){
        when(event){
            is AddEditUserNutritionSettingsEvent.OnOptionChanged -> {
                _selectedOption.value = event.option
                macros[UserNutritionSettingInformation.ValueType.CARB] = event.option.carb
                macros[UserNutritionSettingInformation.ValueType.FAT] = event.option.fat
                macros[UserNutritionSettingInformation.ValueType.PROTEIN] = event.option.protein
            }
            is AddEditUserNutritionSettingsEvent.MacroValueChanged -> {
                if(selectedOption.value != UserNutritionSettingInformation.Option.Custom){
                    _selectedOption.value = UserNutritionSettingInformation.Option.Custom
                }
                macros[event.option] = event.value
            }
        }
    }
    
}