package com.notifyhistory.domain.manager

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import com.judemanutd.autostarter.AutoStartPermissionHelper
import com.notifyhistory.data.prefs.AppPrefs
import com.notifyhistory.data.service.HistoryNotificationListener
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PermissionManagerImpl @Inject constructor(
    @ApplicationContext val context: Context,
    val appPrefs: AppPrefs
) : PermissionManager {

    // Arguments taken from SettingsActivity from android source code.
    companion object {
        private const val EXTRA_SHOW_FRAGMENT_ARGUMENTS = ":settings:show_fragment_args"
        private const val EXTRA_FRAGMENT_ARG_KEY = ":settings:fragment_args_key"
        private const val EXTRA_SECURITY_LISTENER_NAME = "enabled_notification_listeners"
    }

    override fun isNotificationPermissionGranted(): Boolean {
        val securityName = EXTRA_SECURITY_LISTENER_NAME
        val contentResolver = context.contentResolver
        val packageName = context.packageName
        return Settings.Secure.getString(contentResolver, securityName).contains(packageName)
    }

    override fun isBatteryRestricted(): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return !powerManager.isIgnoringBatteryOptimizations(context.packageName)
    }

    override fun isAutoStartSuggested(): Boolean {
        return appPrefs.isAutoStartPermissionSuggested()
    }

    override fun requestNotificationPermission() {
        val name = ComponentName(context, HistoryNotificationListener::class.java).flattenToString()
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Intent(Settings.ACTION_NOTIFICATION_LISTENER_DETAIL_SETTINGS).apply {
                putExtra(Settings.EXTRA_NOTIFICATION_LISTENER_COMPONENT_NAME, name)
            }
        } else {
            // Use fragment arguments to navigate to the details screen prior to Android R
            Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS).apply {
                putExtra(EXTRA_FRAGMENT_ARG_KEY, name)
                putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, Bundle().also {
                    it.putString(EXTRA_FRAGMENT_ARG_KEY, name)
                })
            }
        }
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    @SuppressLint("BatteryLife")
    override fun requestBatteryPermissions() {
        val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        intent.data = Uri.parse("package:${context.packageName}")
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    override fun requestAutoStartPermissions() {
        appPrefs.setAutoStartPermissionSuggested()
        AutoStartPermissionHelper.getInstance().getAutoStartPermission(
            context = context,
            open = true,
            newTask = true
        )
    }

}