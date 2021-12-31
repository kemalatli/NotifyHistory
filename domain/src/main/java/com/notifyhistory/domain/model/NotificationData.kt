package com.notifyhistory.domain.model

import com.notifyhistory.data.model.NotificationEntity

sealed class NotificationData {

    object Idle : NotificationData()

    object Loading : NotificationData()

    object NeedPermission : NotificationData()

    class Data(val list: List<NotificationEntity>) : NotificationData()

}