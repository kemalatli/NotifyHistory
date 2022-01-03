package com.notifyhistory.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.notifyhistory.android.R
import com.notifyhistory.android.ui.composables.*
import com.notifyhistory.android.ui.main.MainViewModel
import com.notifyhistory.domain.model.NotificationData

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {

    // Get notification data
    val notificationData by mainViewModel.recentNotifications.collectAsState(NotificationData.Idle)

    Column(modifier = Modifier.fillMaxSize()) {
        AppToolBar(mainViewModel = mainViewModel)
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
        Box(modifier = Modifier.fillMaxSize()) {
            when (notificationData) {
                is NotificationData.Data -> Notifications((notificationData as NotificationData.Data).list)
                NotificationData.Idle, NotificationData.Loading -> Loading()
                NotificationData.BatteryOptimized -> BatteryOptimized(mainViewModel)
                NotificationData.NeedPermission -> NeedPermission(mainViewModel)
                NotificationData.AutoStartDisabled -> AutoStartNeeded(mainViewModel)
            }
        }
    }

}