package com.zaklabs.pokey.core.network.api

import com.zaklabs.pokey.core.model.dto.PokemonDTO
import com.zaklabs.pokey.core.model.dto.PokemonInfoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Pokemon service definition
 *
 */
interface IPokeyApiService {
    /**
     * Get pokemons
     *
     * @param limit
     * @param offset
     * @return
     */
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0,
    ): Response<PokemonDTO>

    /**
     * Get pokemon info
     *
     * @param name
     * @return
     */
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String,
    ): Response<PokemonInfoDTO>
}
