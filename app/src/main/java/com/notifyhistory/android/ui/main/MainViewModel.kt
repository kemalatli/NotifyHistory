package com.notifyhistory.android.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notifyhistory.domain.model.NotificationData
import com.notifyhistory.domain.usecase.GetRecentNotifications
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    val getRecentNotifications: GetRecentNotifications
) : ViewModel() {

    private val _recentNotifications: MutableStateFlow<NotificationData> =
        MutableStateFlow(NotificationData.Idle)
    val recentNotifications: Flow<NotificationData> = _recentNotifications

    fun requestNotificationPermission() {
        getRecentNotifications.permissionManager.requestNotificationPermission()
    }

    fun requestBatteryPermission() {
        getRecentNotifications.permissionManager.requestBatteryPermissions()
    }

    fun refreshNotifications() {
        Timber.d("Refreshing notifications")
        viewModelScope.launch {
            getRecentNotifications.invoke().collectLatest {
                _recentNotifications.value = it
            }
        }
    }

    fun requestAutoStartPermission() {
        getRecentNotifications.permissionManager.requestAutoStartPermissions()
    }

}