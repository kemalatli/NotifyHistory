package com.notifyhistory.data.service

import android.service.notification.NotificationListenerService
import timber.log.Timber

class HistoryNotificationListener: NotificationListenerService() {


    override fun onListenerConnected() {
        super.onListenerConnected()
        Timber.d("Listener connected!")
    }

}