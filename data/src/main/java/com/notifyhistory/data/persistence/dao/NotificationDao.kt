package com.notifyhistory.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.notifyhistory.data.model.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NotificationDao {

    @Insert(onConflict = REPLACE)
    abstract suspend fun insert(vararg notificationEntity: NotificationEntity)

    @Query("UPDATE notifications set isActive=0")
    abstract suspend fun deleteAllActive()

    @Query("SELECT * FROM notifications sorted where isActive=1 order by isActive desc, postTime desc limit 20")
    abstract fun getRecentActiveNotifications(): Flow<List<NotificationEntity>>

    @Query("SELECT * FROM notifications sorted order by isActive desc, postTime desc limit 20")
    abstract fun getRecentNotifications(): Flow<List<NotificationEntity>>

}