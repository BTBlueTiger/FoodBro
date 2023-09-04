package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general.ProductGeneral
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenFoodFactViewModel @Inject constructor(
    private val foodFactsRepository: OpenFoodFactsRepository
) : ViewModel() {


    private val _productGeneral = mutableStateOf<ProductGeneral?>(null)
    val productGeneral: State<ProductGeneral?> = _productGeneral

    init {
        this.viewModelScope.launch {
            /*
            val response = OpenFoodFactsService.create().getFoodFactsByBarcode(barcode)
            if(response != null){
                extractor = OpenFoodFactsResponseExtractorImpl(response)
                val generalProduct = extractor.getGeneralProduct()
                if(generalProduct != null)
                    _productGeneral.value = generalProduct
            }

             */
        }
    }

}
