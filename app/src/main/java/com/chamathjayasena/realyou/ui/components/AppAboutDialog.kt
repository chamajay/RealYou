/*
 * RealYou
 * Copyright (C) 2025 Chamath Jayasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.chamathjayasena.realyou.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.chamathjayasena.realyou.R
import com.chamathjayasena.realyou.ui.theme.RealYouTheme

/**
 * An app about dialog component.
 *
 * @param icon the icon to be displayed in the dialog.
 * @param appName the name of the application.
 * @param appVersion the version of the application.
 * @param copyrightText optional copyright text to be displayed.
 * @param githubRepoUrl optional URL to the GitHub repository.
 * @param onDismissRequest callback to be invoked when the dialog is dismissed.
 */
@Composable
fun AppAboutDialog(
    icon: Painter,
    appName: String,
    appVersion: String,
    copyrightText: String? = null,
    githubRepoUrl: String? = null,
    onDismissRequest: () -> Unit,
) {
    val uriHandler = LocalUriHandler.current

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                // Same min width and max height as an AlertDialog
                .sizeIn(minWidth = 280.dp, maxHeight = 580.dp)
                .padding(16.dp),
            border = BorderStroke(1.dp, SegmentedButtonDefaults.colors().inactiveBorderColor),
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = icon,
                    contentDescription = "$appName app icon",
                    modifier = Modifier
                        .size(85.dp)
                )
                Text(
                    text = appName,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "Version: $appVersion")
                copyrightText?.let {
                    Text(text = copyrightText)
                }
                githubRepoUrl?.let {
                    Spacer(modifier = Modifier.size(8.dp))
                    IconTextButton(
                        icon = painterResource(R.drawable.github_24px),
                        text = stringResource(R.string.view_on_github),
                        onClick = { uriHandler.openUri(githubRepoUrl) }
                    )
                }
            }
        }
    }
}

@Preview(name = "About Dialog")
@Preview(name = "About Dialog (Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppInfoDialogPreview() {
    RealYouTheme {
        AppAboutDialog(
            icon = painterResource(R.drawable.face_24px),
            appName = stringResource(R.string.app_name),
            appVersion = stringResource(R.string.app_version),
            copyrightText = stringResource(R.string.copyright),
            githubRepoUrl = stringResource(R.string.github_repo),
            onDismissRequest = { },
        )
    }
}
