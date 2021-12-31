package com.notifyhistory.android.ui.main

import androidx.lifecycle.ViewModel
import com.notifyhistory.domain.manager.PermissionManager
import com.notifyhistory.domain.model.NotificationData
import com.notifyhistory.domain.usecase.GetRecentNotifications
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    val getRecentNotifications: GetRecentNotifications
) : ViewModel() {

    private val lastUpdated = MutableStateFlow(System.currentTimeMillis())

    val recentNotifications: Flow<NotificationData> = lastUpdated.flatMapLatest {
        getRecentNotifications.invoke()
    }

    fun requestNotificationPermission() {
        getRecentNotifications.permissionManager.requestNotificationPermission()
    }

    fun refreshNotifications() {
        lastUpdated.value = System.currentTimeMillis()
    }

}