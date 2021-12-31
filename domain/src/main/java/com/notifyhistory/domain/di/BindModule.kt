package com.notifyhistory.domain.di

import com.notifyhistory.domain.manager.PermissionManager
import com.notifyhistory.domain.manager.PermissionManagerImpl
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
    abstract fun bindPermissionManager(permissionManagerImpl: PermissionManagerImpl): PermissionManager

}