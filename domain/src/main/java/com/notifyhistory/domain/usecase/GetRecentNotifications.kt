package com.notifyhistory.domain.usecase

import android.content.Context
import android.os.Build
import android.os.PowerManager
import com.notifyhistory.data.repository.NotificationRepository
import com.notifyhistory.domain.manager.PermissionManager
import com.notifyhistory.domain.model.NotificationData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecentNotifications @Inject constructor(
    @ApplicationContext val context:Context,
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

        // Check battery optimizations
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        if(!powerManager.isIgnoringBatteryOptimizations(context.packageName)){
            emit(NotificationData.BatteryOptimized)
            return@flow
        }


        val notifications = notificationRepository.getRecentNotifications()
        emitAll(notifications.map { NotificationData.Data(it) })

    }

}