package com.zaklabs.pokey.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zaklabs.pokey.R
import com.zaklabs.pokey.ui.screens.navigation.InfoScreen
import com.zaklabs.pokey.ui.screens.navigation.PokeyNavHost
import com.zaklabs.pokey.ui.theme.PokeyTheme

/**
 * Pokey app
 *
 * @param modifier
 */
@Composable
fun PokeyApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val title = backStackEntry?.destination?.hasRoute<InfoScreen>().let { hasRoute ->
        when (hasRoute) {
            true -> {
                val route: InfoScreen = backStackEntry!!.toRoute<InfoScreen>()
                route.pokemon.name.capitalize(Locale.current)
            }

            else -> {
                stringResource(R.string.title_pokemons)
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            PokeyAppBar(
                title = title,
                canNavigateBack = backStackEntry?.destination?.hasRoute<InfoScreen>() ?: false,
                navigateUp = { navController.navigateUp() },
            )
        },
    ) { innerPadding ->
        PokeyNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
@PreviewLightDark
private fun PokeyAppPreview() {
    PokeyTheme {
        PokeyApp()
    }
}
