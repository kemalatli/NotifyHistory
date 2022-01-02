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
fun BatteryOptimized(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.notification_battery_optmized_title))
        Text(text = stringResource(id = R.string.notification_battery_optmized_subtitle))
        Button(onClick = {
            mainViewModel.requestBatteryPermission()
        }) {
            Text(text = stringResource(id = R.string.notification_battery_optmized_action))
        }
    }
}