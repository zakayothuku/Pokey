package com.zaklabs.pokey.core.database

import com.zaklabs.pokey.core.model.entity.PokemonEntity
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow

/**
 * Pokey database interface
 *
 */
interface IPokeyDatabase {
    /**
     * Cache pokemons
     *
     * @param pokemons
     */
    suspend fun cachePokemons(pokemons: RealmList<PokemonEntity>)

    /**
     * Get pokemons
     *
     * @return
     */
    suspend fun getPokemons(): Flow<RealmList<PokemonEntity>>

    /**
     * Cache pokemon info
     *
     * @param pokemonEntity
     */
    suspend fun cachePokemonInfo(pokemonEntity: PokemonEntity)

    /**
     * Get pokemon info
     *
     * @param name
     * @return
     */
    suspend fun getPokemonInfo(name: String): Flow<PokemonEntity?>
}
