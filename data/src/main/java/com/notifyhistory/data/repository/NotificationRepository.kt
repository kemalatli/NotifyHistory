package com.notifyhistory.data.repository

import com.notifyhistory.data.model.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    fun getRecentNotifications(showOnlyActive:Boolean): Flow<List<NotificationEntity>>

    fun persistNotifications(clearActiveItems: Boolean, vararg notifications: NotificationEntity)

}