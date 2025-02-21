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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chamathjayasena.realyou.R
import com.chamathjayasena.realyou.ui.theme.RealYouTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.shouldShowRationale

/**
 * The camera permission request screen.
 *
 * @param cameraPermissionState the camera [PermissionState].
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionScreen(
    cameraPermissionState: PermissionState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
            // If the user has denied the permission but the rationale can be shown
            stringResource(R.string.camera_permission_rationale)
        } else {
            // If it's the first time the user lands on this feature, or the user
            // doesn't want to be asked again for this permission
            stringResource(R.string.camera_permission_required)
        }

        Text(
            text = textToShow,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.camera_permission_info),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { cameraPermissionState.launchPermissionRequest() }
        ) {
            Text(stringResource(R.string.grant_camera_permission))
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview(name = "Camera Permission Screen")
@Preview(name = "Camera Permission Screen (Dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CameraPermissionScreenPreview() {
    val mockPermissionState = object : PermissionState {
        override val permission: String = "android.permission.Camera"
        override val status: PermissionStatus =
            PermissionStatus.Granted

        override fun launchPermissionRequest() {}
    }

    RealYouTheme {
        CameraPermissionScreen(
            cameraPermissionState = mockPermissionState
        )
    }
}
