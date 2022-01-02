package com.notifyhistory.data.di

import com.notifyhistory.data.prefs.AppPrefs
import com.notifyhistory.data.prefs.AppPrefsImpl
import com.notifyhistory.data.repository.NotificationRepository
import com.notifyhistory.data.repository.NotificationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Singleton
    @Binds
    abstract fun bindNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository

    @Singleton
    @Binds
    abstract fun bindAppPrefs(prefs: AppPrefsImpl): AppPrefs

}