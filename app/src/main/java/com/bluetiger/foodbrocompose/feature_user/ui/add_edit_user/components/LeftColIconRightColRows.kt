package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun LeftColIconRightColRows(imageVector: ImageVector, rowList: @Composable () -> List<Unit>) {
    Column {
        Row {
            Column(
                Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(imageVector, contentDescription = "")
            }
            Column(Modifier.weight(1f)) {
                rowList.invoke()
            }
        }
    }
}