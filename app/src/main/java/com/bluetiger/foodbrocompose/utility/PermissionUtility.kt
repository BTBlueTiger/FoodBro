package com.bluetiger.foodbrocompose.utility

import android.Manifest
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.app.ActivityCompat
import com.bluetiger.foodbrocompose.permission.PermissionState

object PermissionUtility {

    fun camaraPermission(context: Context) : PermissionState {
        context.getActivity()?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(Manifest.permission.CAMERA), 1)
        }
        return isPermissionGranted(Manifest.permission.CAMERA, context)
    }

    private fun isPermissionGranted(permission: String, context: Context): PermissionState =
        when(ActivityCompat.checkSelfPermission(context, permission)){
            PackageManager.PERMISSION_GRANTED -> PermissionState.GRANTED
            PackageManager.PERMISSION_DENIED -> {
                val activity = context.getActivity()
                if(activity == null){
                    Log.e("Activity", "Null")
                    PermissionState.DENIED
                } else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
                        PermissionState.RATIONAL
                    } else {
                        PermissionState.DENIED
                    }
                }
            }
            // Should never happen
            else -> { PermissionState.UNKNOWN }
        }


    private fun Context.getActivity(): AppCompatActivity? = when (this) {
        is AppCompatActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> null
    }

}