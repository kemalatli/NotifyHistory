package com.notifyhistory.android.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.notifyhistory.android.R
import com.notifyhistory.android.ui.main.MainViewModel

@Composable
fun NeedPermission(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    PermissionCore(
        modifier = modifier,
        icon = R.drawable.ic_baseline_notification_important_24,
        title = stringResource(id = R.string.notification_permission_needed_title),
        subtitle = stringResource(id = R.string.notification_permission_needed_subtitle),
        action = stringResource(id = R.string.notification_permission_needed_action),
        onClick = {
            mainViewModel.requestNotificationPermission()
        }
    )
}