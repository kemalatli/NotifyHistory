package com.notifyhistory.android.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.notifyhistory.android.ui.composables.*
import com.notifyhistory.android.ui.main.MainViewModel
import com.notifyhistory.domain.model.NotificationData

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {

    // Get notification data
    val notificationData by mainViewModel.recentNotifications.collectAsState(NotificationData.Idle)

    when (notificationData) {
        is NotificationData.Data -> Notifications((notificationData as NotificationData.Data).list)
        NotificationData.Idle, NotificationData.Loading -> Loading()
        NotificationData.BatteryOptimized -> BatteryOptimized(mainViewModel)
        NotificationData.NeedPermission -> NeedPermission(mainViewModel)
        NotificationData.AutoStartDisabled -> AutoStartNeeded(mainViewModel)
    }

}