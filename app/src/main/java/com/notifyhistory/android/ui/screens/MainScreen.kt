package com.notifyhistory.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.notifyhistory.android.ui.composables.*
import com.notifyhistory.android.ui.main.MainViewModel
import com.notifyhistory.domain.model.NotificationData

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {

    // Get notification data
    val notificationData by mainViewModel.recentNotifications.collectAsState(NotificationData.Idle)

    Box(modifier = Modifier.fillMaxSize()) {
        when (notificationData) {
            is NotificationData.Data -> AppScaffold(
                notificationData = notificationData,
                mainViewModel = mainViewModel
            ) {
                Notifications((notificationData as NotificationData.Data).list)
            }
            NotificationData.Idle, NotificationData.Loading -> AppScaffold(
                notificationData = notificationData,
                mainViewModel = mainViewModel
            ) {
                Loading()
            }
            NotificationData.BatteryOptimized -> AppScaffold(
                notificationData = notificationData,
                mainViewModel = mainViewModel
            ) {
                BatteryOptimized(mainViewModel)
            }
            NotificationData.NeedPermission -> AppScaffold(
                notificationData = notificationData,
                mainViewModel = mainViewModel
            ) {
                NeedPermission(mainViewModel)
            }
            NotificationData.AutoStartDisabled -> AppScaffold(
                notificationData = notificationData,
                mainViewModel = mainViewModel
            ) {
                AutoStartNeeded(mainViewModel)
            }
        }
    }

}

@Composable
fun AppScaffold(
    notificationData: NotificationData,
    mainViewModel: MainViewModel,
    body: @Composable () -> Unit
) {
    if (notificationData.showMainScreen) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppToolBar(mainViewModel = mainViewModel)
            Filters(mainViewModel = mainViewModel)
            body.invoke()
        }
    } else {
        body.invoke()
    }
}