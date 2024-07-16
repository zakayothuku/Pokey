package com.zaklabs.pokey.core.network.api

import com.zaklabs.pokey.core.model.dto.PokemonDTO
import com.zaklabs.pokey.core.model.dto.PokemonInfoDTO
import retrofit2.Response

/**
 * Pokemon client interface
 *
 */
interface IPokeyApiClient {
    /**
     * Get pokemons
     *
     */
    suspend fun getPokemons(): Response<PokemonDTO>

    /**
     * Get pokemon info
     *
     */
    suspend fun getPokemonInfo(name: String): Response<PokemonInfoDTO>
}
