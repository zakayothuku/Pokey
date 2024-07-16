package com.zaklabs.pokey.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmpalette.palette.graphics.Palette
import com.zaklabs.pokey.R
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import com.zaklabs.pokey.ui.components.card.BaseCard
import com.zaklabs.pokey.ui.extensions.colorFromPalette
import com.zaklabs.pokey.ui.theme.PokeyTheme

/**
 * Pokemon card
 *
 * @param modifier
 */
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backgroundColor by palette.colorFromPalette()

    BaseCard(
        modifier = modifier,
        backgroundColor = backgroundColor,
        clickable = true,
        onClick = { onClick(pokemon) },
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            PokemonImage(
                size = dimensionResource(R.dimen.pokemon_image_size),
                imageUrl = pokemon.imageUrl,
                onUpdatePalette = { palette = it },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Text(
                text = pokemon.name.capitalize(Locale.current),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = PokeyTheme.spacers.medium),
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun PokemonCardPreview() {
    PokeyTheme {
        Surface {
            PokemonCard(
                pokemon = Pokemon(
                    id = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                ),
                onClick = {},
            )
        }
    }
}
