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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.chamathjayasena.realyou.ui.theme.RealYouTheme

/**
 * An outlined camera preview card component displaying a camera preview.
 *
 * @param previewView the [PreviewView] for the camera preview.
 * @param previewTitle optional title for the camera preview.
 * @param mirrored whether the preview is mirrored or not.
 * @param modifier the modifier for the card.
 */
@Composable
fun OutlinedCameraPreviewCard(
    previewView: PreviewView,
    previewTitle: String? = null,
    mirrored: Boolean = false,
    modifier: Modifier,
) {
    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = SegmentedButtonDefaults.colors().activeContainerColor
        )
    ) {
        Column {
            AndroidView(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                factory = { previewView },
                update = { view ->
                    view.scaleX = if (mirrored) 1f else -1f
                }
            )
            previewTitle?.let {
                Text(
                    text = previewTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 * The mock outlined camera preview card component for previewing [OutlinedCameraPreviewCard].
 * A placeholder is used for the camera preview.
 *
 * @param previewTitle optional title for the camera preview.
 * @param modifier the modifier for the card.
 */
@Composable
private fun MockOutlinedCameraPreviewCard(
    previewTitle: String? = null,
    modifier: Modifier,
) {
    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = SegmentedButtonDefaults.colors().activeContainerColor
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                Text(
                    text = "Camera Preview Placeholder",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            previewTitle?.let {
                Text(
                    text = previewTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(name = "Mock Outlined Camera Preview Card")
@Preview(name = "Mock Outlined Camera Preview Card (Dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MockOutlinedCameraPreviewCardPreview() {
    RealYouTheme {
        MockOutlinedCameraPreviewCard(
            previewTitle = "Mirrored",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(name = "Mock Outlined Camera Preview Card (No Title)")
@Composable
fun MockOutlinedCameraPreviewCardNoTitlePreview() {
    RealYouTheme {
        MockOutlinedCameraPreviewCard(modifier = Modifier.fillMaxSize())
    }
}
