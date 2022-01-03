package com.notifyhistory.data.service

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.notifyhistory.data.model.NotificationEntity
import com.notifyhistory.data.repository.NotificationRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HistoryNotificationListener : NotificationListenerService() {

    @Inject
    lateinit var notificationRepository: NotificationRepository

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Ensure we have the latest notifications when app starts the service
        retrieveActiveNotifications()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Timber.d("Listener connected!")
        retrieveActiveNotifications()
    }


    override fun onNotificationPosted(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationPosted(sbn, rankingMap)
        // Persist posted items
        sbn?.let {
            notificationRepository.persistNotifications(false, it.asEntity(true))
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
            notificationRepository.persistNotifications(false, it.asEntity(false))
        }
    }

    private fun retrieveActiveNotifications() {
        // Persist active items
        val activeNotifications = activeNotifications.map { it.asEntity(true) }
        notificationRepository.persistNotifications(
            clearActiveItems = true,
            notifications = activeNotifications.toTypedArray()
        )
    }

    private fun StatusBarNotification.asEntity(isActive: Boolean): NotificationEntity {
        val title: String = notification?.extras?.getCharSequence("android.title")?.toString() ?: ""
        val text: String = notification?.extras?.getCharSequence("android.text")?.toString() ?: ""
        return NotificationEntity(
            key = key,
            sbnId = id,
            title = title,
            text = text,
            packageName = packageName,
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