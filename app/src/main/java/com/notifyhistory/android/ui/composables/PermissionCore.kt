package com.notifyhistory.android.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun PermissionCore(
    @DrawableRes icon: Int,
    title: String,
    subtitle: String,
    action: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = icon),
            contentDescription = title,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = subtitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onClick
        ) {
            Text(text = action)
        }
    }
}