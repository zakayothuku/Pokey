package com.zaklabs.pokey.ui.components.state

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.zaklabs.pokey.R
import com.zaklabs.pokey.ui.theme.PokeyTheme
import kotlinx.coroutines.delay

/**
 * Empty state
 *
 * @param title
 * @param description
 * @param icon
 * @param modifier
 */
@Composable
fun EmptyState(
    title: String,
    description: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    iconColor: Color = Color.Unspecified,
) {
    var rotationAngle by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {
        // Start rotation when the composable enters the composition
        while (true) {
            rotationAngle += 360f
            delay(10000) // Rotate every 10 seconds
        }
    }

    val animatedRotation by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "rotate",
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(PokeyTheme.spacers.medium),
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = iconColor,
            modifier = Modifier
                .size(dimensionResource(R.dimen.avatar_xlarge))
                .rotate(animatedRotation),
        )
        Text(
            modifier = Modifier.padding(top = PokeyTheme.spacers.small),
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.padding(top = PokeyTheme.spacers.small),
            text = description,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * Empty state preview
 *
 */
@Composable
@PreviewLightDark
private fun EmptyStatePreview() {
    PokeyTheme {
        Surface {
            EmptyState(
                title = "No Pokemons",
                description = "There are no pokemons in your list",
                icon = painterResource(id = R.drawable.ic_pokemon),
                iconColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
