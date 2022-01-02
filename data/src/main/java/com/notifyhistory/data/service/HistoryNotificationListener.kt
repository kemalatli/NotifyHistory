package com.notifyhistory.data.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.notifyhistory.data.model.NotificationEntity
import com.notifyhistory.data.persistence.LocalDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HistoryNotificationListener : NotificationListenerService() {

    @Inject
    lateinit var localDatabase: LocalDatabase

    @Inject
    lateinit var coroutineScope: CoroutineScope

    override fun onListenerConnected() {
        super.onListenerConnected()
        Timber.d("Listener connected!")
        // Persist active items
        val activeNotifications = activeNotifications.map { it.asEntity(true) }
        persistNotifications(
            clearActiveItems = true,
            notificationEntity = activeNotifications.toTypedArray()
        )
    }


    override fun onNotificationPosted(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationPosted(sbn, rankingMap)
        // Persist posted items
        sbn?.let {
            persistNotifications(false, it.asEntity(true))
        }
    }

    override fun onNotificationRemoved(
        sbn: StatusBarNotification?,
        rankingMap: RankingMap?,
        reason: Int
    ) {
        super.onNotificationRemoved(sbn, rankingMap, reason)
        // Persist removed items
        sbn?.let {
            persistNotifications(false, it.asEntity(false))
        }
    }

    private fun persistNotifications(
        clearActiveItems: Boolean,
        vararg notificationEntity: NotificationEntity
    ) {
        coroutineScope.launch {
            if (clearActiveItems) localDatabase.notificationDao().deleteAllActive()
            localDatabase.notificationDao().insert(*notificationEntity)
        }
    }

    private fun StatusBarNotification.asEntity(isActive: Boolean): NotificationEntity {
        val title: String = notification?.extras?.getString("android.title") ?: ""
        val text: String = notification?.extras?.getString("android.text") ?: ""
        return NotificationEntity(
            key = key,
            sbnId = id,
            title = title,
            text = text,
            packageName = packageName,
            tickerText = notification?.tickerText.toString(),
            group = notification?.group ?: "",
            visibility = notification?.visibility ?: 0,
            postTime = postTime,
            isGroup = isGroup,
            isOngoing = isOngoing,
            isClearable = isClearable,
            isActive = isActive
        )
    }

}