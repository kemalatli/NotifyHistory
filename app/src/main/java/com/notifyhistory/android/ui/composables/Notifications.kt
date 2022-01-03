package com.notifyhistory.android.ui.composables

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.notifyhistory.android.R
import com.notifyhistory.android.ui.extensions.ago
import com.notifyhistory.android.ui.theme.Shapes
import com.notifyhistory.data.model.NotificationEntity
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun Notifications(
    notifications: List<NotificationEntity>
) {

    // Set list isEmpty
    var isEmpty by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = notifications, block = {
        delay(500)
        isEmpty = notifications.isEmpty()
    })

    if (notifications.isEmpty()) {
        EmptyNotifications()
    } else {
        AnimatedVisibility(
            visible = !isEmpty,
            enter = fadeIn(0f, tween(1000)),
            exit = fadeOut(0f, tween(1000))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                notifications.groupBy { it.isActive }.forEach { (isActive, list) ->
                    stickyHeader {
                        NotificationHeader(isActive)
                    }
                    itemsIndexed(list, { _, item -> item.key }) { index, item ->
                        NotificationItem(
                            item, Modifier.animateEnterExit(
                                enter = slideInVertically({ it * (index + 1) })
                            )
                        )
                    }
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
fun NotificationItem(notification: NotificationEntity, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val icon: Drawable by remember {
        mutableStateOf(context.packageManager.getApplicationIcon(notification.packageName))
    }
    val appName: CharSequence by remember {
        mutableStateOf(
            context.packageManager.getApplicationLabel(
                context.packageManager.getApplicationInfo(notification.packageName, 0)
            )
        )
    }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(Shapes.large)
            .padding(vertical = 8.dp),
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = rememberDrawablePainter(icon),
                contentDescription = notification.title
            )
            Column(Modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = appName.toString(),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface
                )

                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = notification.text,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = notification.ago(context),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
fun NotificationHeader(isActive: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = if (isActive)
                stringResource(id = R.string.notifications_active)
            else
                stringResource(id = R.string.notifications_dismissed),
            style = MaterialTheme.typography.h6
        )
    }
}