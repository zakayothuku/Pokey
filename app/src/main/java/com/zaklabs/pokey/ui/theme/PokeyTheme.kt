package com.zaklabs.pokey.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.core.view.WindowCompat
import com.zaklabs.pokey.ui.theme.token.colors.ColorsDefaults.darkColorScheme
import com.zaklabs.pokey.ui.theme.token.colors.ColorsDefaults.lightColorScheme
import com.zaklabs.pokey.ui.theme.token.elevations.Elevations
import com.zaklabs.pokey.ui.theme.token.elevations.ElevationsDefaults
import com.zaklabs.pokey.ui.theme.token.elevations.LocalElevations
import com.zaklabs.pokey.ui.theme.token.shapes.LocalShapes
import com.zaklabs.pokey.ui.theme.token.shapes.ShapesDefaults
import com.zaklabs.pokey.ui.theme.token.spacers.LocalSpacers
import com.zaklabs.pokey.ui.theme.token.spacers.Spacers
import com.zaklabs.pokey.ui.theme.token.spacers.SpacersDefaults
import com.zaklabs.pokey.ui.theme.token.typography.LocalTypography
import com.zaklabs.pokey.ui.theme.token.typography.TypographyDefaults
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Contains functions to access the current theme values provided at the
 * call site's position in the hierarchy.
 */
object PokeyTheme {
    val spacers: Spacers
        @Composable @ReadOnlyComposable
        get() = LocalSpacers.current

    val elevations: Elevations
        @Composable @ReadOnlyComposable
        get() = LocalElevations.current
}

@Composable
fun PokeyTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    typography: Typography = TypographyDefaults.V1,
    shapes: Shapes = ShapesDefaults.V1,
    spacers: Spacers = SpacersDefaults.V1,
    elevations: Elevations = ElevationsDefaults.V1,
    content: @Composable () -> Unit,
) {
    val rememberedTypography = remember { typography }
    val rememberedShapes = remember { shapes }
    val rememberedSpacers = remember { spacers }
    val rememberedElevations = remember { elevations }

    val defaultColorScheme =
        when {
            isDarkTheme -> darkColorScheme
            else -> lightColorScheme
        }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = defaultColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
        }
    }

    CompositionLocalProvider(
        LocalTypography provides rememberedTypography,
        LocalShapes provides rememberedShapes,
        LocalSpacers provides rememberedSpacers,
        LocalElevations provides rememberedElevations,
        LocalFontFamilyResolver provides createFontFamilyResolver(
            LocalContext.current,
            CoroutineExceptionHandler { _, e -> e.printStackTrace() },
        ),
    ) {
        MaterialTheme(
            colorScheme = defaultColorScheme,
            typography = typography,
            shapes = shapes,
            content = content,
        )
    }
}
