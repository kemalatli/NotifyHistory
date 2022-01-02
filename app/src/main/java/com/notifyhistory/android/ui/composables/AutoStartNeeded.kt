package com.notifyhistory.android.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.notifyhistory.android.R
import com.notifyhistory.android.ui.main.MainViewModel

@Composable
fun AutoStartNeeded(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.notification_auto_start_needed_title))
        Text(text = stringResource(id = R.string.notification_auto_start_needed_subtitle))
        Button(onClick = {
            mainViewModel.requestAutoStartPermission()
        }) {
            Text(text = stringResource(id = R.string.notification_auto_start_needed_action))
        }
    }
}