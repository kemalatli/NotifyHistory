package com.notifyhistory.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        // Plan timber for logging
        Timber.plant(Timber.DebugTree())
    }

}