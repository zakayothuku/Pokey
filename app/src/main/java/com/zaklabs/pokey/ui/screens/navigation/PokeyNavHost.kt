package com.zaklabs.pokey.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import com.zaklabs.pokey.ui.extensions.serializableType
import com.zaklabs.pokey.ui.screens.home.HomeScreen
import com.zaklabs.pokey.ui.screens.home.HomeViewModel
import com.zaklabs.pokey.ui.screens.info.InfoScreen
import com.zaklabs.pokey.ui.screens.info.InfoViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf

@Serializable
data object HomeScreen

@Serializable
data class InfoScreen(val pokemon: Pokemon) {
    companion object {
        val typeMap = mapOf(typeOf<Pokemon>() to serializableType<Pokemon>())

        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<InfoScreen>(typeMap)
    }
}

/**
 * Pokey nav host
 *
 * @param navController
 * @param modifier
 */
@Composable
fun PokeyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeScreen,
    ) {
        composable<HomeScreen> {
            val viewModel = koinViewModel<HomeViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            HomeScreen(
                uiState = uiState,
                onClick = { pokemon -> navController.navigate(InfoScreen(pokemon = pokemon)) },
            )
        }
        composable<InfoScreen>(typeMap = InfoScreen.typeMap) {
            val viewModel = koinViewModel<InfoViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            InfoScreen(
                name = viewModel.pokemon.value.name.capitalize(Locale.current),
                uiState = uiState,
            )
        }
    }
}
