package com.notifyhistory.domain.manager

interface PermissionManager {

    fun isNotificationPermissionGranted(): Boolean
    fun requestNotificationPermission()

}