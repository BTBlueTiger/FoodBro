package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.nutrition_settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSetting
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserNutritionSettingsViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val pendingMap by lazy { userUseCases.pendingInformation.newUser.getValue() }
    private val newUserNutritionSetting by lazy { pendingMap[UserNutritionSetting::class] as UserNutritionSetting }

    private val _selectedOption = mutableStateOf(newUserNutritionSetting.option)
    val selectedOption : State<UserNutritionSetting.Option> = _selectedOption


    private val _macros = UserNutritionSetting.ValueType.values().map {
        it to 0
    }.toMutableStateMap()

    val macros : SnapshotStateMap<UserNutritionSetting.ValueType, Int> = _macros

    fun onEvent(event: AddEditUserNutritionSettingsEvent){
        when(event){
            is AddEditUserNutritionSettingsEvent.OnOptionChanged -> {
                _selectedOption.value = event.option
                macros[UserNutritionSetting.ValueType.CARB] = event.option.carb
                macros[UserNutritionSetting.ValueType.FAT] = event.option.fat
                macros[UserNutritionSetting.ValueType.PROTEIN] = event.option.protein
            }
            is AddEditUserNutritionSettingsEvent.MacroValueChanged -> {
                if(newUserNutritionSetting.option != UserNutritionSetting.Option.Custom) {
                    _selectedOption.value = UserNutritionSetting.Option.Custom
                }
                macros[event.option] = event.value
            }
        }
    }
    
}