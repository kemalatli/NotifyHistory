package com.notifyhistory.data.di

import android.content.Context
import androidx.room.Room
import com.notifyhistory.data.persistence.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, LocalDatabase::class.java, "offline.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        val context = Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }
        return CoroutineScope(context)
    }

}