package com.notifyhistory.data.prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPrefsImpl @Inject constructor(
    @ApplicationContext val context: Context
) : AppPrefs {

    private val sharedPrefs = context.getSharedPreferences("prefs", MODE_PRIVATE)

    override fun isAutoStartPermissionSuggested(): Boolean {
        return sharedPrefs.getBoolean("isAutoStartPermissionSuggested", false)
    }

    override fun setAutoStartPermissionSuggested() {
        sharedPrefs.edit().putBoolean("isAutoStartPermissionSuggested", true).apply()
    }

}