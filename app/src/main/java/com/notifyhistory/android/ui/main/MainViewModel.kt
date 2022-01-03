package com.notifyhistory.android.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notifyhistory.android.ui.extensions.getStateFlow
import com.notifyhistory.domain.model.NotificationData
import com.notifyhistory.domain.usecase.GetRecentNotifications
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecentNotifications: GetRecentNotifications
) : ViewModel() {

    companion object {
        const val SHOW_ONLY_ACTIVE = "show-only-active"
    }

    private val showOnlyActive: Flow<Boolean> =
        savedStateHandle.getStateFlow(viewModelScope, SHOW_ONLY_ACTIVE, false)
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
            showOnlyActive
                .flatMapLatest { showOnlyActive ->
                    getRecentNotifications.invoke(showOnlyActive)
                }.collectLatest { data ->
                    _recentNotifications.value = data
                }
        }
    }

    fun requestAutoStartPermission() {
        getRecentNotifications.permissionManager.requestAutoStartPermissions()
    }

    fun showActiveNotifications() {
        savedStateHandle.set(SHOW_ONLY_ACTIVE, true)
    }

    fun showAll() {
        val showActive = showingOnlyActiveNotifications()
        savedStateHandle.set(SHOW_ONLY_ACTIVE, false)
    }

    fun showingOnlyActiveNotifications() = savedStateHandle.get<Boolean>(SHOW_ONLY_ACTIVE) ?: false

}