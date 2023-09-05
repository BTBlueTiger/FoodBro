package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.OpenFoodFactDataUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenFoodFactViewModel @Inject constructor(
    private val foodFactDataUseCases: OpenFoodFactDataUseCases,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _openFoodFactsData = mutableStateOf(OpenFoodFactsData())
    val openFoodFactsData : State<OpenFoodFactsData> = _openFoodFactsData


    fun getBrands() = openFoodFactsData.value.productGeneral?.brands
    fun getImageUrl() : ByteArray? = openFoodFactsData.value.productGeneral?.imageByteArray

    init {
        val desiredFoodData = FBPreferences.getInstance().getDesiredOpenFoodFactsData()
        viewModelScope.launch {
            // If -1 is set, the last Response should be taken
            if(desiredFoodData > 0) {
                _openFoodFactsData.value =
                    foodFactDataUseCases.getOpenFoodFactByTimeStamp(desiredFoodData)
            }
            else {
                _openFoodFactsData.value = foodFactDataUseCases.getLastOpenFoodFactData()
            }
        }
    }

}
