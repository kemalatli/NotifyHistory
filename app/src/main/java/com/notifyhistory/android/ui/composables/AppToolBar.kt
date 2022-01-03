package com.notifyhistory.android.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.notifyhistory.android.R
import com.notifyhistory.android.ui.main.MainViewModel

@Composable
fun AppToolBar(mainViewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = "App Logo",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h5
        )
    }
}