package com.notifyhistory.domain.usecase

import com.notifyhistory.data.repository.NotificationRepository
import com.notifyhistory.domain.manager.PermissionManager
import com.notifyhistory.domain.model.NotificationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecentNotifications @Inject constructor(
    val permissionManager: PermissionManager,
    val notificationRepository: NotificationRepository
) {

    operator fun invoke(): Flow<NotificationData> = flow {

        // Set loading state
        emit(NotificationData.Loading)

        // Check notification permission
        if (!permissionManager.isNotificationPermissionGranted()) {
            emit(NotificationData.NeedPermission)
            return@flow
        }
        // Has permission get notifications
        val notifications = notificationRepository.getRecentNotifications()
        emitAll(notifications.map { NotificationData.Data(it) })

    }

}