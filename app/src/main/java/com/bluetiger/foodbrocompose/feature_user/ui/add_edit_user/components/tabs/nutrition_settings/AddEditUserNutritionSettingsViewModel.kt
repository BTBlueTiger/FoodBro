package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.nutrition_settings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.ui.common.components.slider.SliderStateColors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserNutritionSettingsViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val TAG = this::class.toString()

    private val pendingMap by lazy { userUseCases.pendingInformation.newUser.getValue() }
    private var newUserNutritionSettingInformation =
        pendingMap[UserNutritionSettingInformation::class] as UserNutritionSettingInformation

    private val _selectedOption = mutableStateOf(
        UserNutritionSettingInformation.Option.values()
            .findLast { newUserNutritionSettingInformation.optionName == it.settingsName }!!
    )
    val selectedOption: State<UserNutritionSettingInformation.Option> = _selectedOption


    private val _macros = UserNutritionSettingInformation.ValueType.values().map {
        it to 0
    }.toMutableStateMap()

    val macros: SnapshotStateMap<UserNutritionSettingInformation.ValueType, Int> = _macros

    init {
        newUserNutritionSettingInformation.iterator().forEach {
            macros[it.first] = it.second
        }
    }

    private fun updatePendingMap(userNutritionSettingInformation: UserNutritionSettingInformation) {
        pendingMap[UserNutritionSettingInformation::class] = userNutritionSettingInformation
        Log.i(TAG, newUserNutritionSettingInformation.toString())
        userUseCases.pendingInformation.newUser.setValue(pendingMap)
    }

    fun onEvent(event: AddEditUserNutritionSettingsEvent) {
        when (event) {
            is AddEditUserNutritionSettingsEvent.OnOptionChanged -> {
                _selectedOption.value = event.option
                macros[UserNutritionSettingInformation.ValueType.CARB] = event.option.carb
                macros[UserNutritionSettingInformation.ValueType.FAT] = event.option.fat
                macros[UserNutritionSettingInformation.ValueType.PROTEIN] = event.option.protein
                newUserNutritionSettingInformation = newUserNutritionSettingInformation.copy(
                    carb = event.option.carb,
                    protein = event.option.protein,
                    fat = event.option.fat,
                    optionName = event.option.settingsName
                )
                updatePendingMap(newUserNutritionSettingInformation)
            }

            is AddEditUserNutritionSettingsEvent.MacroValueChanged -> {
                if (selectedOption.value != UserNutritionSettingInformation.Option.Custom) {
                    _selectedOption.value = UserNutritionSettingInformation.Option.Custom
                }
                macros[event.option] = event.value
                newUserNutritionSettingInformation = newUserNutritionSettingInformation.customCopy(
                    event.option.memberParam, event.value
                ) as UserNutritionSettingInformation
                updatePendingMap(newUserNutritionSettingInformation)
            }
        }
    }

    fun getDifference() = 100f - macros.values.sum()

    fun getSliderColor(): SliderStateColors {
        val sum = macros.values.sum()
        return when {
            sum == 100 -> {
                SliderStateColors.VALID
            }
            sum > 100 -> {
                SliderStateColors.ERROR
            }
            sum < 100 -> {
                SliderStateColors.PENDING
            }
            else -> {
                SliderStateColors.DEFAULT
            }
        }
    }


}