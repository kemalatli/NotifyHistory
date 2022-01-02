package com.notifyhistory.data.repository

import androidx.core.app.NotificationCompat
import com.notifyhistory.data.model.NotificationEntity
import com.notifyhistory.data.persistence.LocalDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepositoryImpl @Inject constructor(
    private val localDatabase: LocalDatabase,
    private val coroutineScope: CoroutineScope
) : NotificationRepository {

    override fun getRecentNotifications(): Flow<List<NotificationEntity>> {
        return localDatabase.notificationDao().getRecentNotifications()
    }

    override fun persistNotifications(
        clearActiveItems: Boolean,
        vararg notifications: NotificationEntity
    ) {
        coroutineScope.launch {
            val items = notifications
                .filter { it.visibility == NotificationCompat.VISIBILITY_PUBLIC }
                .toTypedArray()
            Timber.d("Persisting ${items.size} items")
            if (clearActiveItems) localDatabase.notificationDao().deleteAllActive()
            localDatabase.notificationDao().insert(*items)
        }
    }

}