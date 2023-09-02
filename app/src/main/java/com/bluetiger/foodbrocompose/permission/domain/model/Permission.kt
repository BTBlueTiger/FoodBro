package com.bluetiger.foodbrocompose.permission.domain.model

import com.bluetiger.foodbrocompose.permission.PermissionState

data class Permission(
    val permission :  String,
    val requestCode : Int,
    val permissionState: PermissionState,
)