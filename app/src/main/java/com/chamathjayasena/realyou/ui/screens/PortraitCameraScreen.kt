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

package com.chamathjayasena.realyou.ui.screens

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.chamathjayasena.realyou.R
import com.chamathjayasena.realyou.ui.components.AppAboutDialog
import com.chamathjayasena.realyou.ui.components.OutlinedCameraPreviewCard
import com.chamathjayasena.realyou.ui.components.SegmentedViewButtons
import com.chamathjayasena.realyou.utils.initializeCamera

/**
 * The portrait camera screen.
 * Only the real view is used, which is flipped
 * horizontally to toggle between real and mirrored previews.
 *
 * @param context the application context
 * @param lifecycleOwner the lifecycle owner
 * @param realPreviewView the real preview view
 */
@Composable
fun PortraitCameraScreen(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    realPreviewView: PreviewView
) {
    var mirrored by remember { mutableStateOf(false) }
    var openAboutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        initializeCamera(
            context, lifecycleOwner,
            realPreviewView
        )
    }
    if (openAboutDialog) {
        AppAboutDialog(
            icon = painterResource(R.drawable.face_24px),
            appName = stringResource(R.string.app_name),
            appVersion = stringResource(R.string.app_version),
            copyrightText = stringResource(R.string.copyright),
            githubRepoUrl = stringResource(R.string.github_repo),
            onDismissRequest = { openAboutDialog = false }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .safeDrawingPadding()
    ) {
        OutlinedCameraPreviewCard(
            previewView = realPreviewView,
            mirrored = mirrored,
            modifier = Modifier
                .weight(7f)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { mirrored = !mirrored } // Toggle mirrored view
                    )
                }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedIconButton(
                onClick = { openAboutDialog = true },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = SegmentedButtonDefaults.colors()
                        .inactiveContainerColor,
                    contentColor = SegmentedButtonDefaults.colors()
                        .inactiveContentColor
                ),
                border = BorderStroke(
                    1.dp, SegmentedButtonDefaults.colors().inactiveBorderColor
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.info_24px),
                    contentDescription = stringResource(R.string.about_ic_desc)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            SegmentedViewButtons(
                mirrored = mirrored,
                onClick = { isMirrored -> mirrored = isMirrored }
            )
        }
    }
}
