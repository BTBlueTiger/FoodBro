package com.bluetiger.foodbrocompose.permission.use_case

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.bluetiger.foodbrocompose.permission.PermissionState
import com.bluetiger.foodbrocompose.permission.domain.model.Permission
import com.bluetiger.foodbrocompose.permission.data.repository.PermissionRepository
/*

class PermissionCamara(private val repository: PermissionRepository) {

    companion object {
        const val REQUEST_CODE = 10
    }

    private val _permissionState: MutableState<Permission> =
        mutableStateOf(
            Permission(Manifest.permission.CAMERA, REQUEST_CODE, PermissionState.UNKNOWN)
        )
    val permissionState: State<Permission> = _permissionState


    operator fun invoke(context: Context): PermissionCamara {
        _permissionState.value = repository.checkSelf(context, permissionState.value)
        Log.i("PermissionUseCase", permissionState.value.toString())
        return this
    }

    fun permissionGranted(): Boolean =
        permissionState.value.permissionState == PermissionState.GRANTED

    fun permissionDenied(): Boolean =
        permissionState.value.permissionState == PermissionState.DENIED


    fun requestPermission(context: Context) {
        repository.requestPermission(permissionState.value, context)
    }

    fun setRequestedPermissionResult(result: PermissionState) {
        _permissionState.value = permissionState.value.copy(
            permissionState = result
        )
    }
}



 */