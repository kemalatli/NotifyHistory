package com.notifyhistory.data.repository

import com.notifyhistory.data.model.NotificationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepositoryImpl @Inject constructor(
) : NotificationRepository {

    override fun getRecentNotifications(): Flow<List<NotificationEntity>> {
        return flowOf(listOf())
    }

}