package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components

import android.content.Context
import android.util.Log
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.CameraInfo
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.Executors

@androidx.camera.core.ExperimentalGetImage
class BarcodeScannerVideoView {
    private val cameraExecutor = Executors.newSingleThreadExecutor()
    private var mContext: Context? = null

    private val requireContext
        get() = mContext!!

    private val previewView by lazy {
        PreviewView(requireContext).apply {
            scaleType = PreviewView.ScaleType.FILL_CENTER
        }
    }

    private val preview by lazy {
        Preview.Builder()
            .build().also { it.setSurfaceProvider(previewView.surfaceProvider) }
    }

    private val imageAnalyzer by lazy {
        ImageAnalysis.Builder()
            .setTargetResolution(Size(1920, 1080))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }
    private val imageCapture = ImageCapture.Builder().build()

    private val cameraProviderFuture by lazy {
        ProcessCameraProvider.getInstance(requireContext)
    }

    @Composable
    fun VideoCapture(
        modifier: Modifier = Modifier,
        onBarcodeFound: (String) -> Unit
    ) {
        AndroidView(

            factory = { context ->
                mContext = context

                cameraProviderFuture.addListener({
                    val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                    val camaraInfo: CameraInfo? =
                        cameraProvider.availableCameraInfos[CameraSelector.LENS_FACING_BACK]

                    imageAnalyzer.setAnalyzer(cameraExecutor, BarcodeScanner {
                        onBarcodeFound(it)
                    })

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            context as ComponentActivity,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            imageCapture,
                            imageAnalyzer
                        )



                    } catch (exc: Exception) {
                        Log.e("DEBUG", "Use case binding failed", exc)
                    }
                }, ContextCompat.getMainExecutor(context))

                previewView
            },
            modifier = modifier
        )
    }
}
