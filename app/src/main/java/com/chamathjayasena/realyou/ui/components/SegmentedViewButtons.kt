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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chamathjayasena.realyou.R
import com.chamathjayasena.realyou.ui.theme.RealYouTheme

/**
 * A segmented buttons component for selecting between real and mirrored camera previews.
 *
 * @param mirrored whether the camera preview is mirrored or not.
 * @param onClick callback to be invoked when a [SegmentedButton] button is clicked.
 */
@Composable
fun SegmentedViewButtons(
    mirrored: Boolean,
    onClick: (Boolean) -> Unit,
) {
    // List of pairs of labels and corresponding boolean values (mirrored or not).
    val viewOptions = listOf(
        Pair(stringResource(R.string.real), false),
        Pair(stringResource(R.string.mirrored), true),
    )

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        viewOptions.forEachIndexed { index, (label, isMirrored) ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = viewOptions.size),
                onClick = { onClick(isMirrored) },
                selected = isMirrored == mirrored
            ) {
                Text(label)
            }
        }
    }
}

@Preview(name = "Segmented Buttons")
@Preview(name = "Segmented Buttons (Dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SegmentedButtonsPreview() {
    RealYouTheme {
        SegmentedViewButtons(mirrored = false, onClick = {})
    }
}
