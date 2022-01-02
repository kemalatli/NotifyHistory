package com.notifyhistory.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.notifyhistory.data.model.NotificationEntity
import com.notifyhistory.data.persistence.dao.NotificationDao

@Database(
    entities = [
        NotificationEntity::class
    ], version = 1
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun notificationDao(): NotificationDao

}