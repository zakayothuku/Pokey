package com.zaklabs.pokey.core.network.api

import com.zaklabs.pokey.core.model.dto.PokemonDTO
import com.zaklabs.pokey.core.model.dto.PokemonInfoDTO
import retrofit2.Response

/**
 * Pokey api client
 *
 * @property service
 */
class PokeyApiClient(
    private val service: IPokeyApiService,
) : IPokeyApiClient {
    override suspend fun getPokemons(): Response<PokemonDTO> = service.getPokemons()

    override suspend fun getPokemonInfo(name: String): Response<PokemonInfoDTO> = service.getPokemonInfo(name)
}
