package com.zaklabs.pokey.core.data

import com.zaklabs.pokey.core.model.resource.Resource
import com.zaklabs.pokey.core.model.uimodel.PokemonInfo
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Pokemon repository interface
 *
 */
interface IPokeyRepository {
    /**
     * Get pokemons
     *
     * @return
     */
    suspend fun getPokemons(): Flow<Resource<List<Pokemon>?>>

    /**
     * Get pokemon info
     *
     * @param name
     * @return
     */
    suspend fun getPokemonInfo(name: String): Flow<Resource<PokemonInfo?>>
}
