package com.zaklabs.pokey.ui.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.kmpalette.palette.graphics.Palette

@Composable
internal fun Palette?.colorFromPalette(alpha: Float = .1f): State<Color> {
    val defaultBackground = MaterialTheme.colorScheme.background
    return remember(this) {
        derivedStateOf { this?.dominantSwatch?.rgb?.let { Color(it).copy(alpha = alpha) } ?: defaultBackground }
    }
}
