package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general.ProductGeneral
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenFoodFactViewModel @Inject constructor(
    private val foodFactsRepository: OpenFoodFactsRepository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _openFoodFactsData = mutableStateOf(OpenFoodFactsData())
    val openFoodFactsData : State<OpenFoodFactsData> = _openFoodFactsData


    fun getBrands() = openFoodFactsData.value.productGeneral?.brands
    fun getImageUrl() = openFoodFactsData.value.productGeneral?.imageUrl

    init {
        val desiredBarcode = FBPreferences.getInstance().getDesiredOpenFoodFactsData()
        viewModelScope.launch {
            _openFoodFactsData.value =
                foodFactsRepository.getOpenFoodFactResponseByTimeStamp(desiredBarcode)
        }
    }

}
