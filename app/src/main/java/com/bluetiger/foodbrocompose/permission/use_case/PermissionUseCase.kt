package com.bluetiger.foodbrocompose.permission.use_case

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.bluetiger.foodbrocompose.permission.PermissionState
import com.bluetiger.foodbrocompose.permission.data.repository.PermissionRepository
import com.bluetiger.foodbrocompose.permission.domain.model.Permission

sealed class PermissionUseCase(protected val repository: PermissionRepository) {

    protected abstract val defaultPermission : Permission

    protected abstract val _permissionState:  MutableState<Permission>

    abstract val permissionState : State<Permission>

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

    operator fun invoke(context: Context): PermissionUseCase {
        _permissionState.value = repository.checkSelf(context, permissionState.value)
        Log.i("PermissionUseCase", permissionState.value.toString())
        return this
    }

    class Camara(repository: PermissionRepository) : PermissionUseCase(repository) {
        companion object{
            const val REQUEST_CODE = 10
        }
        override val defaultPermission: Permission = Permission(Manifest.permission.CAMERA, REQUEST_CODE, PermissionState.UNKNOWN)
        override val _permissionState: MutableState<Permission> = mutableStateOf(defaultPermission)
        override val permissionState: State<Permission> = _permissionState
    }

}