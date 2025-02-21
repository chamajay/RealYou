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
import android.content.res.Configuration
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

/**
 * The camera screen.
 */
@Composable
fun CameraScreen() {
    val configuration: Configuration = LocalConfiguration.current
    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    // Two views are used.
    // In portrait mode, the real view is flipped horizontally for a mirrored effect.
    // In landscape mode, both real and mirrored views are used.
    val realPreviewView = remember {
        PreviewView(context).apply {
            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        }
    }
    val mirroredPreviewView = remember { PreviewView(context) }

    if (isLandscape(configuration)) {
        LandscapeCameraScreen(
            context, lifecycleOwner, realPreviewView, mirroredPreviewView
        )
    } else {
        PortraitCameraScreen(
            context, lifecycleOwner, realPreviewView
        )
    }
}

/**
 * Checks if the current orientation is landscape.
 *
 * @param configuration the configuration.
 * @return `true` if the orientation is landscape, `false` otherwise.
 */
fun isLandscape(configuration: Configuration): Boolean {
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}
