package com.zaklabs.pokey.ui.theme.token.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

internal object ColorsDefaults {
    val lightColorScheme
        @Composable @ReadOnlyComposable
        get() = lightColorScheme(
            primary = Color(0xFF735B0C),
            onPrimary = Color(0xFFFFFFFF),
            primaryContainer = Color(0xFFFFE08D),
            onPrimaryContainer = Color(0xFF241A00),
            secondary = Color(0xFF2C638B),
            onSecondary = Color(0xFFFFFFFF),
            secondaryContainer = Color(0xFFCCE5FF),
            onSecondaryContainer = Color(0xFF001D31),
            tertiary = Color(0xFF316A42),
            onTertiary = Color(0xFFFFFFFF),
            tertiaryContainer = Color(0xFFB3F1BE),
            onTertiaryContainer = Color(0xFF00210C),
            error = Color(0xFFBA1A1A),
            onError = Color(0xFFFFFFFF),
            errorContainer = Color(0xFFFFDAD6),
            onErrorContainer = Color(0xFF410002),
            background = Color(0xFFFFF8F1),
            onBackground = Color(0xFF1F1B13),
            surface = Color(0xFFFFF8F1),
            onSurface = Color(0xFF1F1B13),
            surfaceVariant = Color(0xFFEBE1CF),
            onSurfaceVariant = Color(0xFF4C4639),
            outline = Color(0xFF7E7667),
            outlineVariant = Color(0xFFCFC5B4),
            scrim = Color(0xFF000000),
            inverseSurface = Color(0xFF343027),
            inverseOnSurface = Color(0xFFF8F0E2),
            inversePrimary = Color(0xFFE3C36C),
            surfaceDim = Color(0xFFE1D9CC),
            surfaceBright = Color(0xFFFFF8F1),
            surfaceContainerLowest = Color(0xFFFFFFFF),
            surfaceContainerLow = Color(0xFFFBF3E5),
            surfaceContainer = Color(0xFFF5EDDF),
            surfaceContainerHigh = Color(0xFFF0E7D9),
            surfaceContainerHighest = Color(0xFFEAE1D4),
        )

    val darkColorScheme
        @Composable @ReadOnlyComposable
        get() = darkColorScheme(
            primary = Color(0xFFE3C36C),
            onPrimary = Color(0xFF3D2F00),
            primaryContainer = Color(0xFF584400),
            onPrimaryContainer = Color(0xFFFFE08D),
            secondary = Color(0xFF99CCF9),
            onSecondary = Color(0xFF003351),
            secondaryContainer = Color(0xFF074B72),
            onSecondaryContainer = Color(0xFFCCE5FF),
            tertiary = Color(0xFF98D5A4),
            onTertiary = Color(0xFF003919),
            tertiaryContainer = Color(0xFF16512C),
            onTertiaryContainer = Color(0xFFB3F1BE),
            error = Color(0xFFFFB4AB),
            onError = Color(0xFF690005),
            errorContainer = Color(0xFF93000A),
            onErrorContainer = Color(0xFFFFDAD6),
            background = Color(0xFF16130B),
            onBackground = Color(0xFFEAE1D4),
            surface = Color(0xFF16130B),
            onSurface = Color(0xFFEAE1D4),
            surfaceVariant = Color(0xFF4C4639),
            onSurfaceVariant = Color(0xFFCFC5B4),
            outline = Color(0xFF989080),
            outlineVariant = Color(0xFF4C4639),
            scrim = Color(0xFF000000),
            inverseSurface = Color(0xFFEAE1D4),
            inverseOnSurface = Color(0xFF343027),
            inversePrimary = Color(0xFF735B0C),
            surfaceDim = Color(0xFF16130B),
            surfaceBright = Color(0xFF3D392F),
            surfaceContainerLowest = Color(0xFF110E07),
            surfaceContainerLow = Color(0xFF1F1B13),
            surfaceContainer = Color(0xFF231F17),
            surfaceContainerHigh = Color(0xFF2D2A21),
            surfaceContainerHighest = Color(0xFF39342B),
        )
}
