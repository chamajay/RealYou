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

import android.Manifest
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

/**
 * The main screen.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen() {
    val cameraPermissionState: PermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    if (isCameraPermissionGranted(cameraPermissionState)) {
        CameraScreen()
    } else {
        CameraPermissionScreen(cameraPermissionState)
    }
}

/**
 * Checks if the camera permission is granted.
 *
 * @param cameraPermissionState the camera [PermissionState].
 * @return `true` if the camera permission is granted, `false` otherwise.
 */
@OptIn(ExperimentalPermissionsApi::class)
fun isCameraPermissionGranted(cameraPermissionState: PermissionState): Boolean {
    return cameraPermissionState.status.isGranted
}
