package com.notifyhistory.domain.manager

interface PermissionManager {

    fun isNotificationPermissionGranted(): Boolean
    fun isBatteryRestricted(): Boolean
    fun isAutoStartSuggested(): Boolean
    fun requestNotificationPermission()
    fun requestBatteryPermissions()
    fun requestAutoStartPermissions()

}