package com.zaklabs.pokey.ui.theme.token.spacers

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Holds the space design tokens for a particular theme.
 */
class Spacers(
    val none: Dp = 0.dp,
    val xxxSmall: Dp = 0.dp,
    val xxSmall: Dp = 0.dp,
    val xSmall: Dp = 0.dp,
    val small: Dp = 0.dp,
    val medium: Dp = 0.dp,
    val large: Dp = 0.dp,
    val xLarge: Dp = 0.dp,
    val xxLarge: Dp = 0.dp,
)

internal val LocalSpacers = staticCompositionLocalOf { Spacers() }
