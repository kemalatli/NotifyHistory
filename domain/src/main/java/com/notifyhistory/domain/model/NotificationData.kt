package com.notifyhistory.domain.model

import com.notifyhistory.data.model.NotificationEntity

sealed class NotificationData(val showMainScreen: Boolean) {

    object Idle : NotificationData(false)

    object Loading : NotificationData(true)

    object NeedPermission : NotificationData(false)

    object BatteryOptimized : NotificationData(false)

    object AutoStartDisabled : NotificationData(false)

    class Data(val list: List<NotificationEntity>) : NotificationData(true)

}