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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.chamathjayasena.realyou.R
import com.chamathjayasena.realyou.ui.components.OutlinedCameraPreviewCard
import com.chamathjayasena.realyou.utils.initializeCamera

/**
 * The landscape camera screen.
 * Real and mirrored previews are shown side by side.
 *
 * @param context the application context.
 * @param lifecycleOwner the lifecycle owner.
 * @param realPreviewView the real preview view.
 * @param mirroredPreviewView the mirrored preview view.
 */
@Composable
fun LandscapeCameraScreen(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    realPreviewView: PreviewView,
    mirroredPreviewView: PreviewView,
) {
    LaunchedEffect(Unit) {
        initializeCamera(
            context, lifecycleOwner,
            realPreviewView,
            mirroredPreviewView
        )
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .safeDrawingPadding()
    ) {
        OutlinedCameraPreviewCard(
            previewView = realPreviewView,
            previewTitle = stringResource(R.string.real),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedCameraPreviewCard(
            previewView = mirroredPreviewView,
            previewTitle = stringResource(R.string.mirrored),
            modifier = Modifier.weight(1f)
        )
    }
}
