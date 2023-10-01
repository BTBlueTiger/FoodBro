package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.pre_configured

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.ActivitySettingsType
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.AddEditUserActivityEvent
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.AddEditUserActivityViewModel


@Composable
private fun ActivityCard(
    activity: UserActivityInformation.ValueType,
    selected: Boolean,
    onClick: (UserActivityInformation.ValueType) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick(activity)
                // Show more information when the card is clicked
                // You can implement this logic
                // (e.g., show AlertDialog with more details)
            }
            .then(
                if (selected) Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary, // Customize the border color
                    shape = RoundedCornerShape(8.dp) // You can adjust the corner radius
                ) else Modifier
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = activity.shortTerm,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            if (selected) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                ) {
                    Text(text = activity.examples)
                    Spacer(Modifier.height(2.dp))
                    Text(text = activity.description)
                }
            } else {
                Image(
                    painter = painterResource(id = activity.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Composable
fun PreConfiguredActivityValuesSheet(
    viewModel: AddEditUserActivityViewModel = hiltViewModel()
) {
    val items = UserActivityInformation.ValueType.values().toList()
    var selected by remember { mutableStateOf(viewModel.preConfigured.value) }



    Column {
        LazyVerticalGrid(
            GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { activity ->
                ActivityCard(activity = activity, selected = selected == activity, onClick = {
                    viewModel.onEvent(
                        AddEditUserActivityEvent.PreconfiguredActivityValueChanged(
                            it
                        )
                    )
                    selected = it
                })
            }
        }
    }
}