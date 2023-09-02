package com.bluetiger.foodbrocompose.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

/*
object PermissionRequests {

    @Composable
    fun OnRequest(useCase: PermissionUseCases, doIfGranted: () -> Unit) {
        val granted = mutableStateOf(false)
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission())
            { granted.value = it }.launch(useCase)

    }
}


private fun showDialog() {

}

}

 */