package com.notifyhistory.android.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.notifyhistory.android.R
import com.notifyhistory.data.model.NotificationEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Notifications(
    notifications: List<NotificationEntity>
) {
    if (notifications.isEmpty()) {
        EmptyNotifications()
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            notifications.groupBy { it.isActive }.forEach { (isActive, list) ->
                stickyHeader {
                    Text(
                        text = if (isActive)
                            stringResource(id = R.string.notifications_active)
                        else
                            stringResource(id = R.string.notifications_dismissed),
                        style = MaterialTheme.typography.h4
                    )
                }
                items(list) {
                    NotificationItem(it)
                }
            }
        }
    }
}

@Composable
fun EmptyNotifications() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(id = R.drawable.abc_vector_test),
                contentDescription = "No notifications"
            )
            Text(text = stringResource(id = R.string.notifications_empty))
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