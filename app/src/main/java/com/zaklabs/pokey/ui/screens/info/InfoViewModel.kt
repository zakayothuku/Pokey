@file:OptIn(ExperimentalCoroutinesApi::class)

package com.zaklabs.pokey.ui.screens.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaklabs.pokey.core.data.IPokeyRepository
import com.zaklabs.pokey.core.model.resource.ErrorType
import com.zaklabs.pokey.core.model.resource.Resource
import com.zaklabs.pokey.core.model.resource.UIState
import com.zaklabs.pokey.core.model.uimodel.PokemonInfo
import com.zaklabs.pokey.ui.screens.navigation.InfoScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class InfoViewModel(
    private val repository: IPokeyRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val infoScreen = InfoScreen.from(savedStateHandle)
    private val _pokemon = MutableStateFlow(infoScreen.pokemon)
    val pokemon = _pokemon.asStateFlow()

    val uiState: StateFlow<UIState<PokemonInfo?>> =
        _pokemon.filterNotNull().flatMapLatest { pokemon ->
            repository.getPokemonInfo(name = pokemon.name).map { resource ->
                when (resource) {
                    is Resource.Loading -> UIState.Loading
                    is Resource.Success -> UIState.Success(resource.data)
                    is Resource.Error -> UIState.Error(resource.error ?: ErrorType.Local.Unknown)
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState.Loading,
        )
}
