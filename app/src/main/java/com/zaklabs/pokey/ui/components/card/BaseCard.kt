package com.zaklabs.pokey.ui.components.card

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.zaklabs.pokey.ui.theme.PokeyTheme

/**
 * Base card
 *
 * @param modifier
 * @param backgroundColor
 * @param clickable
 * @param onClick
 * @param content
 * @receiver
 * @receiver
 */
@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    clickable: Boolean = false,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = PokeyTheme.elevations.none),
        shape = MaterialTheme.shapes.medium,
        enabled = clickable,
        onClick = { onClick() },
        colors =
        CardDefaults.cardColors(
            containerColor = backgroundColor,
            disabledContentColor = backgroundColor,
            disabledContainerColor = backgroundColor,
        ),
        modifier =
        modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing,
                ),
            ),
    ) { content() }
}

/**
 * Base card preview
 *
 */
@Composable
@PreviewLightDark
private fun BaseCardPreview() {
    PokeyTheme {
        Surface {
            BaseCard(modifier = Modifier.padding(16.dp)) {
                LazyColumn(Modifier.padding(16.dp)) {
                    items(3) {
                        Text(text = "Card Text", color = MaterialTheme.colorScheme.onBackground)
                    }
                }
            }
        }
    }
}
