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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chamathjayasena.realyou.R
import com.chamathjayasena.realyou.ui.theme.RealYouTheme

/**
 * An icon text button component.
 *
 * @param icon the icon to be displayed.
 * @param iconDesc optional description for the icon.
 * @param text the text to be displayed.
 * @param onClick callback to be invoked when the button is clicked.
 */
@Composable
fun IconTextButton(
    icon: Painter,
    iconDesc: String? = null,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(icon, iconDesc, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }
}

@Preview(name = "Icon Text Button")
@Preview(name = "Icon Text Button (Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun IconTextButtonPreview() {
    RealYouTheme {
        Surface {
            IconTextButton(
                icon = painterResource(R.drawable.github_24px),
                text = "View on GitHub",
                onClick = {}
            )
        }
    }
}
