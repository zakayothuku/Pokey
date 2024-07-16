package com.zaklabs.pokey.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaklabs.pokey.core.data.IPokeyRepository
import com.zaklabs.pokey.core.model.resource.ErrorType
import com.zaklabs.pokey.core.model.resource.Resource
import com.zaklabs.pokey.core.model.resource.UIState
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * HomeScreen view model
 *
 * @property repository
 */
class HomeViewModel(private val repository: IPokeyRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Pokemon>>> = MutableStateFlow(UIState.Loading)
    var uiState = _uiState.asStateFlow()

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            repository.getPokemons().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.value = UIState.Loading
                    }

                    is Resource.Success -> {
                        _uiState.value = UIState.Success(resource.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _uiState.value = UIState.Error(resource.error ?: ErrorType.Local.Unknown)
                    }
                }
            }
        }
    }
}
