package com.zaklabs.pokey.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.zaklabs.pokey.ui.theme.token.font.PokemonGBFont

/**
 * Base text
 *
 * @param text
 * @param color
 * @param fontSize
 * @param modifier
 * @param fontWeight
 */
@Composable
fun BaseText(
    text: String,
    color: Color,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        color = color,
        style = TextStyle(
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = PokemonGBFont,
        ),
        modifier = modifier,
    )
}