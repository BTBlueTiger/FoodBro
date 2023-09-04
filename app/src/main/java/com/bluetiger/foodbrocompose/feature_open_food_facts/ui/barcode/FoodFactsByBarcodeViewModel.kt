package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.repository.OpenFoodFactsResponseExtractor
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.OpenFoodFactsService
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import com.bluetiger.foodbrocompose.permission.use_case.PermissionUseCases
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state.ConditionOutlineTextFieldPack
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors.OutlineTextFieldColorCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodFactsByBarcodeViewModel @Inject constructor(
    private val permissionUseCases: PermissionUseCases,
    private val foodFactsRepository: OpenFoodFactsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private enum class FloatingButtonImage(val imageId: Int) {
        ON(R.drawable.baseline_camera_24),
        OFF(R.drawable.outline_camera_24);

        companion object {
            fun getOpposite(id: Int) =
                if (id == OFF.imageId)
                    ON.imageId
                else {
                    OFF.imageId
                }
        }
    }


    private val _flashMode = mutableStateOf(false)
    val flashMode: State<Boolean> = _flashMode

    private val _camaraScannerShow = mutableStateOf(false)
    val camaraScannerShow: State<Boolean> = _camaraScannerShow


    private val _floatingButtonImage = mutableIntStateOf(R.drawable.baseline_camera_24)
    val floatingButtonImage: State<Int> = _floatingButtonImage

    private val _response: MutableState<OpenFoodFactsData> = mutableStateOf(OpenFoodFactsData( ))
    val response: State<OpenFoodFactsData> = _response


    private val _barcode = mutableStateOf(
        ConditionOutlineTextFieldPack(
            colorCase = OutlineTextFieldColorCases.DEFAULT,
            isError = false,
            supportingText = null,
            value = ""
        )
    )

    val barcode: State<ConditionOutlineTextFieldPack<String>> = _barcode


    fun event(event: FoodFactsByBarcodeEvents) {
        when (event) {
            is FoodFactsByBarcodeEvents.CamaraShowEvent -> {
                viewModelScope.launch {
                    permissionUseCases.camara(event.context).also {
                        if (it.permissionGranted()) {
                            _camaraScannerShow.value = !camaraScannerShow.value
                            _floatingButtonImage.intValue =
                                FloatingButtonImage.getOpposite(event.currentImage)
                        } else if (it.permissionDenied()) {
                            Log.e("Hello", "Hello")
                        } else {
                            it.requestPermission(event.context)
                        }
                    }
                }
            }

            is FoodFactsByBarcodeEvents.ToggleFlashMode -> {
                _flashMode.value = !flashMode.value
            }

            is FoodFactsByBarcodeEvents.BarcodeFound -> {
                _barcode.value = checkValue(event.barcode)
                _camaraScannerShow.value = false
            }

            is FoodFactsByBarcodeEvents.BarcodeEnteredEvent -> {
                _barcode.value = checkValue(event.value)
            }

            is FoodFactsByBarcodeEvents.BarcodeResponseRequest -> {

                if (barcode.value.isValid) {
                    viewModelScope.launch {
                        val response = OpenFoodFactsService.create()
                            .getFoodFactsByBarcode(barcode = _barcode.value.value)
                        val data = OpenFoodFactsResponseExtractor.createData(response)
                        _response.value = data
                        foodFactsRepository.insertOpenFoodFactsResponse(data)
                    }
                }

                this._barcode.value = barcode.value.copy(
                    value = "",
                    isError = false,
                    isValid = false,
                    colorCase = OutlineTextFieldColorCases.DEFAULT,
                    supportingText = null
                )
            }
        }
    }

    private fun checkValue(value: String): ConditionOutlineTextFieldPack<String> {
        var colors: OutlineTextFieldColorCases = OutlineTextFieldColorCases.DEFAULT
        var supportingText: String? = null
        var isError = false
        var isValid = false
        if (value.isNotEmpty()) {
            if (value.length > 13) {
                colors = OutlineTextFieldColorCases.ERROR
                supportingText = "The Barcode is only valid with 8 or 13 digits"
                isError = true
            } else {
                isError = false
                if (value.length == 8 || value.length == 13) {
                    colors = OutlineTextFieldColorCases.VALID
                    supportingText = null
                    isValid = true
                } else {
                    colors = OutlineTextFieldColorCases.PENDING
                    supportingText = "A valid Barcode will contain 8 or 13 digits"
                }
            }
        }
        return ConditionOutlineTextFieldPack(
            value = value,
            colorCase = colors,
            supportingText = { if (supportingText != null) Text(text = supportingText) },
            isError = isError,
            isValid = isValid
        )
    }
}