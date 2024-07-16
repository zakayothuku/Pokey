package com.zaklabs.pokey.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.zaklabs.pokey.R
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import com.zaklabs.pokey.ui.components.BaseText
import com.zaklabs.pokey.ui.theme.PokeyTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PokemonList(
    pokemons: ImmutableList<Pokemon>,
    onClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
    searchState: SearchState = rememberSearchState(),
    listState: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
) {
    val filteredPokemons = if (searchState.searchQuery.isBlank()) {
        pokemons
    } else {
        pokemons.filter {
            it.name.contains(searchState.searchQuery, ignoreCase = true)
        }
    }

    BackHandler(enabled = searchState.isSearchFocused) {
        searchState.isSearchFocused = false
        searchState.searchQuery = ""
    }

    LaunchedEffect(searchState.isSearchFocused) {
        if (!searchState.isSearchFocused) {
            listState.animateScrollToItem(0)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        PokemonSearchField(
            searchState = searchState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(PokeyTheme.spacers.medium)
        )
        LazyVerticalStaggeredGrid(
            modifier = modifier.padding(PokeyTheme.spacers.medium),
            state = listState,
            columns = StaggeredGridCells.Adaptive(dimensionResource(R.dimen.pokemon_image_size)),
            verticalItemSpacing = PokeyTheme.spacers.medium,
            horizontalArrangement = Arrangement.spacedBy(PokeyTheme.spacers.medium),
        ) {
            items(filteredPokemons, key = { pokemon -> pokemon.id }) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onClick = onClick,
                    modifier = Modifier.animateItem(
                        fadeInSpec = tween(250),
                        fadeOutSpec = tween(250),
                    ),
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun PokemonListPreview() {
    PokeyTheme {
        Surface {
            PokemonList(
                pokemons = persistentListOf(
                    Pokemon(
                        id = 1,
                        name = "Bulbasaur",
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    )
                ),
                onClick = {})
        }
    }
}
