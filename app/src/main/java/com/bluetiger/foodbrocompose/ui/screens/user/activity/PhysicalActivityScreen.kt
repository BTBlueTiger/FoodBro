package com.bluetiger.foodbrocompose.ui.screens.user.activity

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import com.bluetiger.foodbrocompose.database.user.ActivityDataValueType
import com.bluetiger.foodbrocompose.ui.common.headline.HeadLine

@Composable
fun PhysicalActivityScreen(){

}

@Composable
fun ActivitySegment(activityDataValueType: ActivityDataValueType) {
    Row {
        Divider()
        HeadLine(headline = activityDataValueType.shortTerm)

        Divider()
    }
}