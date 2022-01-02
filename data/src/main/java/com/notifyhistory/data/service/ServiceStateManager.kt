package com.notifyhistory.data.service

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceStateManager @Inject constructor(
    @ApplicationContext val context: Context,
    private val coroutineScope: CoroutineScope
) {

    fun ensureServiceRunning() {
        coroutineScope.launch {
            context.startService(Intent(context, HistoryNotificationListener::class.java))
        }
    }

}