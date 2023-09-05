package com.bluetiger.foodbrocompose.ui.common.components.async_image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun AsyncImage(modifier: Modifier, url: String?, contentScale: ContentScale) {

    val bitmap = remember { mutableStateOf<Bitmap?>(null) }



    LaunchedEffect(null) {
        try {
            val pendingBitmap = withContext(Dispatchers.IO) {
                BitmapFactory.decodeStream(URL(url).openStream())
            }
            bitmap.value = pendingBitmap
        } catch (e: Exception) {
            if (url.isNullOrEmpty()) {
                Log.e("AsyncImage", "Null or Empty Url")
            } else {
                Log.e("AsyncImage", e.message.toString())
            }
        }
    }

    if (bitmap.value != null)

        Image(
            modifier = modifier,
            bitmap = bitmap.value!!.asImageBitmap(),
            contentDescription = "",
            contentScale = contentScale
        )

}

@Composable
fun AsyncImage(modifier: Modifier, byteArray: ByteArray, contentScale: ContentScale) {

    val bitmap = remember { mutableStateOf<Bitmap?>(null) }


    LaunchedEffect(null) {
        try {
            val pendingBitmap = withContext(Dispatchers.IO) {
                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            }
            bitmap.value = pendingBitmap
        } catch (e: Exception) {
            Log.e("AsyncImage", e.message.toString())
        }
    }


    if (bitmap.value != null)
        Image(
            modifier = modifier,
            bitmap = bitmap.value!!.asImageBitmap(),
            contentDescription = "",
            contentScale = contentScale
        )
}