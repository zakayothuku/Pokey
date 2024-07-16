package com.zaklabs.pokey.ui.screens.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zaklabs.pokey.R
import com.zaklabs.pokey.core.model.resource.UIState
import com.zaklabs.pokey.core.model.uimodel.PokemonInfo
import com.zaklabs.pokey.ui.components.state.EmptyState
import com.zaklabs.pokey.ui.theme.PokeyTheme

/**
 * InfoScreen screen
 *
 * @param name
 * @param uiState
 * @param modifier
 */
@Composable
fun InfoScreen(
    name: String,
    uiState: UIState<PokemonInfo?>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(object : NestedScrollConnection {}),
    ) {
        when (uiState) {
            UIState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    EmptyState(
                        title = stringResource(R.string.label_loading),
                        description = stringResource(R.string.label_pokemon_is_loading, name),
                        icon = painterResource(id = R.drawable.ic_pokemon),
                        iconColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            is UIState.Success -> {
                uiState.data?.let { PokemonInfo(it) }
            }

            is UIState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    EmptyState(
                        title = stringResource(R.string.label_error),
                        description = stringResource(R.string.label_pokemon_failed_to_load, name),
                        icon = painterResource(id = R.drawable.ic_pokemon),
                        iconColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun InfoScreenPreview() {
    PokeyTheme {
        Surface {
            InfoScreen(
                name = "Bulbasaur",
                uiState = UIState.Loading,
            )
        }
    }
}
