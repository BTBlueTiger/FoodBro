package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.OpenFoodFactsService
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.extractor.OpenFoodFactsResponseExtractorImpl
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ProductGeneral
import kotlinx.coroutines.launch

class OpenFoodFactViewModel constructor(
    private val barcode: String
) : ViewModel() {

    private lateinit var extractor : OpenFoodFactsResponseExtractorImpl

    private val _productGeneral = mutableStateOf<ProductGeneral?>(null)
    val productGeneral: State<ProductGeneral?> = _productGeneral

    init {
        this.viewModelScope.launch {
            val response = OpenFoodFactsService.create().getFoodFactsByBarcode(barcode)
            if(response != null){
                extractor = OpenFoodFactsResponseExtractorImpl(response)
                val generalProduct = extractor.getGeneralProduct()
                if(generalProduct != null)
                    _productGeneral.value = generalProduct
            }
        }
    }

}
