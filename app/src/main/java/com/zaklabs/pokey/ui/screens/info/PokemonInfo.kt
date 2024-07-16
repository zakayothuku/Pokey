@file:OptIn(ExperimentalLayoutApi::class)

package com.zaklabs.pokey.ui.screens.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.kmpalette.palette.graphics.Palette
import com.zaklabs.pokey.R
import com.zaklabs.pokey.core.model.uimodel.PokemonInfo
import com.zaklabs.pokey.ui.components.BaseText
import com.zaklabs.pokey.ui.components.card.BaseCard
import com.zaklabs.pokey.ui.extensions.colorFromPalette
import com.zaklabs.pokey.ui.screens.home.PokemonImage
import com.zaklabs.pokey.ui.theme.PokeyTheme

@Composable
fun PokemonInfo(
    info: PokemonInfo,
    modifier: Modifier = Modifier,
) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backgroundColor by palette.colorFromPalette()

    Column(modifier = modifier.fillMaxSize()) {
        BaseCard(
            modifier = Modifier
                .padding(horizontal = PokeyTheme.spacers.medium, vertical = PokeyTheme.spacers.small)
                .fillMaxWidth(),
            backgroundColor = backgroundColor.copy(.5f),
        ) {
            Column(
                modifier = Modifier
                    .padding(PokeyTheme.spacers.medium)
                    .fillMaxWidth()
            ) {
                PokemonImage(
                    size = dimensionResource(R.dimen.pokemon_image_size_large),
                    imageUrl = info.imageUrl ?: "",
                    onUpdatePalette = { palette = it },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = PokeyTheme.spacers.medium),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_weight),
                            tint = Color.Unspecified,
                            contentDescription = "Weight",
                            modifier = Modifier.size(PokeyTheme.spacers.xLarge)
                        )
                        BaseText(
                            text = "${info.weight} kg",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            modifier = Modifier.padding(start = PokeyTheme.spacers.small),
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_height),
                            tint = Color.Unspecified,
                            contentDescription = "Height",
                            modifier = Modifier.size(PokeyTheme.spacers.xLarge)
                        )
                        BaseText(
                            text = "${info.height} m",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            modifier = Modifier.padding(start = PokeyTheme.spacers.small),
                        )
                    }
                }
            }
        }

        BaseCard(
            backgroundColor = MaterialTheme.colorScheme.tertiary.copy(.2f),
            modifier = Modifier
                .padding(horizontal = PokeyTheme.spacers.medium, vertical = PokeyTheme.spacers.small)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = PokeyTheme.spacers.medium,
                        start = PokeyTheme.spacers.medium,
                        end = PokeyTheme.spacers.medium,
                        bottom = PokeyTheme.spacers.small
                    )
                    .fillMaxWidth()
            ) {

                BaseText(
                    text = stringResource(R.string.label_abilities),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                )

                Spacer(modifier = Modifier.size(PokeyTheme.spacers.small))

                FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(PokeyTheme.spacers.small)) {
                    info.abilities.filter { !it.isHidden }.forEach { ability ->
                        SuggestionChip(
                            shape = MaterialTheme.shapes.medium,
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(.2f),
                                labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            ),
                            label = {
                                BaseText(
                                    text = ability.name.capitalize(Locale.current),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                    modifier = Modifier.padding(start = PokeyTheme.spacers.small)
                                )
                            },
                            onClick = {}
                        )
                    }
                }
            }
        }

        BaseCard(
            backgroundColor = MaterialTheme.colorScheme.secondary.copy(.2f),
            modifier = Modifier
                .padding(horizontal = PokeyTheme.spacers.medium, vertical = PokeyTheme.spacers.small)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(PokeyTheme.spacers.medium)
                    .fillMaxWidth()
            ) {

                BaseText(
                    text = stringResource(R.string.label_stats),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                )

                Spacer(modifier = Modifier.size(PokeyTheme.spacers.medium))

                LazyRow (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    items(info.stats) { stat ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(top = PokeyTheme.spacers.small)
                        ) {
                            BaseText(
                                text = "${stat.baseStat}",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            )
                            Spacer(modifier = Modifier.size(PokeyTheme.spacers.medium))
                            BaseText(
                                text = stat.displayName.toUpperCase(Locale.current),
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun PokemonInfoPreview() {
    PokeyTheme {
        Surface {
            PokemonInfo(
                info = PokemonInfo(
                    id = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    order = 2,
                    weight = 200,
                    height = 340,
                    baseExperience = 102,
                    abilities = listOf(
                        PokemonInfo.PokemonAbility(
                            name = "Overgrow",
                            order = 1,
                            isHidden = false,
                        ),
                        PokemonInfo.PokemonAbility(
                            name = "Grass",
                            order = 2,
                            isHidden = false,
                        )
                    ),
                    stats = listOf(
                        PokemonInfo.PokemonStat(
                            name = "hp",
                            baseStat = 45,
                        ),
                        PokemonInfo.PokemonStat(
                            name = "attack",
                            baseStat = 49,
                        ),
                        PokemonInfo.PokemonStat(
                            name = "defense",
                            baseStat = 49,
                        ),
                        PokemonInfo.PokemonStat(
                            name = "speed",
                            baseStat = 45,
                        )
                    ),
                ),
            )
        }
    }
}
