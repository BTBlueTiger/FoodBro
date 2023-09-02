package com.bluetiger.foodbrocompose.permission.data.repository

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import com.bluetiger.foodbrocompose.permission.PermissionState
import com.bluetiger.foodbrocompose.permission.domain.model.Permission

interface PermissionRepository {
    fun checkSelf(context: Context, permission: Permission): Permission
    fun requestPermission(permission: Permission, context: Context)
}