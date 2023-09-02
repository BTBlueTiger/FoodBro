package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode

import android.content.Context

sealed class FoodFactsByBarcodeEvents {
    data class CamaraShowEvent(val context: Context, val currentImage: Int) :
        FoodFactsByBarcodeEvents()

    data class BarcodeFound(val barcode: String) : FoodFactsByBarcodeEvents()
    data class BarcodeEnteredEvent(val value: String) : FoodFactsByBarcodeEvents()
    object BarcodeResponseRequest : FoodFactsByBarcodeEvents()
    object ToggleFlashMode : FoodFactsByBarcodeEvents()
}