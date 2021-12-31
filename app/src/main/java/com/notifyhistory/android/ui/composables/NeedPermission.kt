package com.notifyhistory.android.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.notifyhistory.android.R
import com.notifyhistory.android.ui.main.MainViewModel
import com.notifyhistory.domain.manager.PermissionManager

@Composable
fun NeedPermission(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.notification_permission_needed_title))
        Text(text = stringResource(id = R.string.notification_permission_needed_subtitle))
        Button(onClick = {
            mainViewModel.requestNotificationPermission()
        }) {
            Text(text = stringResource(id = R.string.notification_permission_needed_action))
        }
    }
}