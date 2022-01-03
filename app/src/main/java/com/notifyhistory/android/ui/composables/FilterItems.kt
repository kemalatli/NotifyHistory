package com.notifyhistory.android.ui.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> FilterItems(
    modifier:Modifier,
    title: String,
    items: List<T>,
    selectText: @Composable (T) -> String,
    isSelected: (T) -> Boolean,
    clickOn: (T) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp),
            text = title,
            style = MaterialTheme.typography.h6
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(
                    rememberScrollState()
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach {
                if (isSelected(it)) {
                    Button(onClick = {
                        clickOn(it)
                    }) {
                        Text(text = selectText(it))
                    }
                } else {
                    OutlinedButton(onClick = {
                        clickOn(it)
                    }) {
                        Text(text = selectText(it))
                    }
                }
            }
        }
    }
}