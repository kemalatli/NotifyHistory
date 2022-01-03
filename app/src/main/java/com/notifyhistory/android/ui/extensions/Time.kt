package com.notifyhistory.android.ui.extensions

import android.content.Context
import com.notifyhistory.android.R
import com.notifyhistory.data.model.NotificationEntity
import org.joda.time.*

fun NotificationEntity.ago(context: Context): String {
    var txt = ""
    val dateTime = DateTime(postTime)
    val now = DateTime.now()
    val weeks = Weeks.weeksBetween(dateTime, now).weeks
    if (weeks != 0) {
        txt = context.getString(R.string.core_ago_week, weeks)
    } else {
        val days = Days.daysBetween(dateTime, now).days
        txt = if (days != 0) {
            context.getString(R.string.core_ago_day, days)
        } else {
            val hours = Hours.hoursBetween(dateTime, now).hours
            if (hours != 0) {
                context.getString(R.string.core_ago_hour, hours)
            } else {
                val minutes = Minutes.minutesBetween(dateTime, now).minutes
                if (minutes != 0) {
                    context.getString(R.string.core_ago_minute, minutes)
                } else {
                    context.getString(R.string.core_just_now)
                }
            }
        }
    }
    return txt
}