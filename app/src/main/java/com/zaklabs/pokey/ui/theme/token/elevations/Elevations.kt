package com.zaklabs.pokey.ui.theme.token.elevations

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Holds the elevations design tokens for a particular theme.
 */
class Elevations(
    val none: Dp = 0.dp,
    val xSmall: Dp = 0.dp,
    val small: Dp = 0.dp,
    val medium: Dp = 0.dp,
    val large: Dp = 0.dp,
    val xLarge: Dp = 0.dp,
    val max: Dp = 0.dp,
)

internal val LocalElevations = staticCompositionLocalOf { Elevations() }
