package com.bluetiger.foodbrocompose.ui.common.components.selection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bluetiger.foodbrocompose.ui.common.components.headline.HeadLine

@Composable
fun SelectionGroupMultiSelect(
    label: String = "",
    modifier: Modifier,
    selections: List<Selection>,
    rows: Int,
    heightPerRow: Int = 100,
    widthPerSelection: Int = 100,
    onValueChange: (List<Selection>) -> Unit)
{
    if(label.isNotEmpty())
       HeadLine(headline = label)

    val selectionsPerRow: Int = selections.size / rows

    var selectionCounter = 0
    for (i in 1..rows) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (j in 1..selectionsPerRow) {
                val selection = selections[selectionCounter]
                selectionCounter++
                Card(
                    modifier = Modifier
                        .clickable {
                            selection.clicked = !selection.clicked
                            onValueChange(selections)
                        }
                        .background(
                            if (selection.clicked) MaterialTheme.colorScheme.primary
                            else Color.Transparent
                        )
                        .width(widthPerSelection.dp)
                        .height(heightPerRow.dp), // Distribute the available width evenly among cards
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(
                        Modifier
                            .padding(10.dp)
                            .fillMaxSize(), // Fill the available space to center the content
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painterResource(id = selection.iconId),
                            contentDescription = selection.label
                        )
                        Text(text = selection.label)
                    }
                }
            }
        }
    }
}

