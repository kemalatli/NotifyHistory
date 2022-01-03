package com.notifyhistory.data.repository

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

    override fun getRecentNotifications(showOnlyActive: Boolean): Flow<List<NotificationEntity>> {
        return if (showOnlyActive)
            localDatabase.notificationDao().getRecentActiveNotifications()
        else
            localDatabase.notificationDao().getRecentNotifications()
    }

    override fun persistNotifications(
        clearActiveItems: Boolean,
        vararg notifications: NotificationEntity
    ) {
        coroutineScope.launch {
            Timber.d("Persisting ${notifications.size} items")
            if (clearActiveItems) localDatabase.notificationDao().deleteAllActive()
            localDatabase.notificationDao().insert(*notifications)
        }
    }

}