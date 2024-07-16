package com.zaklabs.pokey.ui.theme.token.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.zaklabs.pokey.R

val PokemonGBFont = FontFamily(
    Font(resId = R.font.pokemon_gb),
    Font(resId = R.font.pokemon_gb, weight = FontWeight.Light),
    Font(resId = R.font.pokemon_gb, weight = FontWeight.Medium),
    Font(resId = R.font.pokemon_gb, weight = FontWeight.SemiBold),
    Font(resId = R.font.pokemon_gb, weight = FontWeight.Bold),
)
