package com.zaklabs.pokey.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import com.zaklabs.pokey.ui.components.BaseText
import com.zaklabs.pokey.ui.theme.PokeyTheme

/**
 * Pokemon search field
 *
 * @param searchState
 * @param modifier
 */
@Composable
fun PokemonSearchField(
    searchState: SearchState,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = searchState.searchQuery,
        onValueChange = { searchState.searchQuery = it },
        label = {
            BaseText(
                text = "Search Pokemons",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                modifier = Modifier.padding(start = PokeyTheme.spacers.small)
            )
        },
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .onFocusChanged { searchState.isSearchFocused = it.isFocused }
    )
}

data class SearchState(
    var isSearchFocused: Boolean = false,
    var searchQuery: String = ""
)

@Composable
fun rememberSearchState(): SearchState = remember { SearchState() }