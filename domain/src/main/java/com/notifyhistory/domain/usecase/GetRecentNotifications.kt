package com.notifyhistory.domain.usecase

import android.content.Context
import com.notifyhistory.data.repository.NotificationRepository
import com.notifyhistory.data.service.ServiceStateManager
import com.notifyhistory.domain.manager.PermissionManager
import com.notifyhistory.domain.model.NotificationData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class GetRecentNotifications @Inject constructor(
    @ApplicationContext val context: Context,
    val permissionManager: PermissionManager,
    val serviceStateManager: ServiceStateManager,
    private val notificationRepository: NotificationRepository
) {

    operator fun invoke(showOnlyActive: Boolean): Flow<NotificationData> = flow {

        // Set loading state
        emit(NotificationData.Loading)

        // Check auto start permission
        Timber.d("Checking auto-start permission")
        if (!permissionManager.isAutoStartSuggested()) {
            Timber.d("App needs to grant auto-start permission")
            emit(NotificationData.AutoStartDisabled)
            return@flow
        }

        // Check battery optimizations
        Timber.d("Checking battery optimizations")
        delay(1000)
        if (permissionManager.isBatteryRestricted()) {
            Timber.d("App needs to stop battery optimizations")
            emit(NotificationData.BatteryOptimized)
            return@flow
        }

        // Check notification permission
        Timber.d("Checking notifications permission")
        if (!permissionManager.isNotificationPermissionGranted()) {
            Timber.d("App needs to grant permission for notification listener")
            emit(NotificationData.NeedPermission)
            return@flow
        }

        Timber.d("All checks completed.")
        serviceStateManager.ensureServiceRunning()
        val notifications = notificationRepository.getRecentNotifications(showOnlyActive)
        emitAll(notifications.map { NotificationData.Data(it) })

    }

}