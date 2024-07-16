package com.zaklabs.pokey.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.zaklabs.pokey.R
import com.zaklabs.pokey.core.model.resource.UIState
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import com.zaklabs.pokey.ui.components.state.EmptyState
import com.zaklabs.pokey.ui.theme.PokeyTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeScreen(
    uiState: UIState<List<Pokemon>>,
    onClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            UIState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    EmptyState(
                        title = stringResource(R.string.label_loading),
                        description = stringResource(R.string.label_collecting_pokemons),
                        icon = painterResource(id = R.drawable.ic_pokemon),
                        iconColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            is UIState.Success -> {
                PokemonList(
                    pokemons = uiState.data.toImmutableList(),
                    onClick = onClick,
                )
            }

            is UIState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    EmptyState(
                        title = stringResource(R.string.label_error),
                        description = stringResource(R.string.label_error_loading_pokemons),
                        icon = painterResource(id = R.drawable.ic_pokemon),
                        iconColor = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun HomeScreenPreview() {
    PokeyTheme {
        Surface {
            HomeScreen(
                uiState = UIState.Loading,
                onClick = { },
            )
        }
    }
}
