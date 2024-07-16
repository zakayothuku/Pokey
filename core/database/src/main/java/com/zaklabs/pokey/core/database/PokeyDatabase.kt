package com.zaklabs.pokey.core.database

import com.zaklabs.pokey.core.model.entity.PokemonEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Pokey database implementation
 *
 * @property realm
 */
class PokeyDatabase(private val realm: Realm) : IPokeyDatabase {

    override suspend fun cachePokemons(pokemons: RealmList<PokemonEntity>) {
        realm.write {
            pokemons.forEach { pokemon ->
                copyToRealm(pokemon, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    override suspend fun getPokemons(): Flow<RealmList<PokemonEntity>> = flow {
        val pokemonsResult = realm.query<PokemonEntity>().find()
        val pokemonList = realmListOf<PokemonEntity>()
        pokemonList.addAll(pokemonsResult)
        emit(pokemonList)
    }

    override suspend fun cachePokemonInfo(pokemonEntity: PokemonEntity) {
        realm.write {
            val existingPokemonEntity = query<PokemonEntity>("id = ${pokemonEntity.id}").first().find()
            if (existingPokemonEntity != null) {
                existingPokemonEntity.apply {
                    name = pokemonEntity.name
                    order = pokemonEntity.order
                    weight = pokemonEntity.weight
                    height = pokemonEntity.height
                    imageUrl = pokemonEntity.imageUrl
                    baseExperience = pokemonEntity.baseExperience
                    abilities = pokemonEntity.abilities
                    stats = pokemonEntity.stats
                }
                copyToRealm(existingPokemonEntity, updatePolicy = UpdatePolicy.ALL)
            }
            copyToRealm(pokemonEntity, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override suspend fun getPokemonInfo(name: String): Flow<PokemonEntity> = flow {
        realm.query<PokemonEntity>("name = $0", name).first().find()?.let { emit(it) }
    }
}
