package com.notifyhistory.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey
    val key: String = "",
    val sbnId: Int = 0,
    val title: String = "",
    val text: String = "",
    val packageName: String = "",
    val group: String = "",
    val visibility: Int = 0,
    val postTime: Long = 0L,
    val isGroup: Boolean = false,
    val isOngoing: Boolean = false,
    val isClearable: Boolean = false,
    val isActive: Boolean = false
)