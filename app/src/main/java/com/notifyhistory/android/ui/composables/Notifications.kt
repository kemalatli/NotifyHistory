package com.notifyhistory.android.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.notifyhistory.android.R
import com.notifyhistory.data.model.NotificationEntity

@Composable
fun Notifications(
    notifications: List<NotificationEntity>
) {
    LazyColumn(modifier=Modifier.fillMaxSize()) {
        items(notifications) {
            NotificationItem(it)
        }
    }
}

@Composable
fun NotificationItem(notification: NotificationEntity) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = notification.title
        )
        Column {
            Text(text = notification.title)
            Text(text = notification.text)
            Text(text = notification.postTime.toString())
        }
    }
}