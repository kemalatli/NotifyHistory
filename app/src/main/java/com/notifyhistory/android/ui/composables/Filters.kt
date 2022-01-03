package com.notifyhistory.android.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.notifyhistory.android.R
import com.notifyhistory.android.ui.main.MainViewModel

@Composable
fun Filters(mainViewModel: MainViewModel) {
    FilterItems(
        modifier = Modifier.padding(horizontal = 16.dp),
        title = stringResource(
            id = R.string.notifications_filter_by
        ),
        items = listOf(false, true),
        selectText = {
            if (it) stringResource(id = R.string.notifications_only_active)
            else stringResource(id = R.string.notifications_all)
        },
        isSelected = { mainViewModel.showingOnlyActiveNotifications() == it },
        clickOn = {
            if (it) mainViewModel.showActiveNotifications() else mainViewModel.showAll()
        }
    )
}