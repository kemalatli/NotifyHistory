package com.notifyhistory.data.repository

import com.notifyhistory.data.model.NotificationEntity
import com.notifyhistory.data.persistence.LocalDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepositoryImpl @Inject constructor(
    private val localDatabase: LocalDatabase
) : NotificationRepository {

    override fun getRecentNotifications(): Flow<List<NotificationEntity>> {
        return localDatabase.notificationDao().getRecentNotifications()
    }

}