package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.barcode_scanner

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_EAN_13
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_EAN_8
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_UPC_A
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_UPC_E
import com.google.mlkit.vision.common.InputImage

class BarcodeScanner(
    val callback: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val options by lazy {
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                FORMAT_EAN_13,
                FORMAT_EAN_8,
                FORMAT_UPC_A,
                FORMAT_UPC_E
            )
            .build()
    }

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(image: ImageProxy) {
        if (true) {
            image.image?.let {
                BarcodeScanning.getClient(options).process(
                    InputImage.fromMediaImage(it, image.imageInfo.rotationDegrees)
                ).addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        callback(barcodes.first().rawValue.toString())
                    }
                }
                    .addOnFailureListener{Log.e("FAIL", "FIASODIJAOSIFHIUAEHf")}
                    .addOnCompleteListener{
                        image.close()
                    }
            }

        }
    }
}
