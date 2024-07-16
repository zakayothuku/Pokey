package com.zaklabs.pokey.core.data

import com.zaklabs.pokey.core.database.IPokeyDatabase
import com.zaklabs.pokey.core.model.dto.PokemonDTO
import com.zaklabs.pokey.core.model.dto.PokemonInfoDTO
import com.zaklabs.pokey.core.model.entity.PokemonEntity
import com.zaklabs.pokey.core.model.mapper.Mappers.toPokemonInfo
import com.zaklabs.pokey.core.model.mapper.Mappers.toPokemonList
import com.zaklabs.pokey.core.model.mapper.Mappers.toPokemonRealm
import com.zaklabs.pokey.core.model.mapper.Mappers.toRealmList
import com.zaklabs.pokey.core.model.mapper.RepositoryMapper
import com.zaklabs.pokey.core.model.resource.Resource
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import com.zaklabs.pokey.core.model.uimodel.PokemonInfo
import com.zaklabs.pokey.core.network.api.IPokeyApiClient
import com.zaklabs.pokey.core.network.networkHandler
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow

/**
 * Pokey repository implementation
 *
 * @property database
 * @property client
 */
class PokeyRepository(
    private val database: IPokeyDatabase,
    private val client: IPokeyApiClient,
) : IPokeyRepository {

    override suspend fun getPokemons(): Flow<Resource<List<Pokemon>?>> {
        return networkHandler(
            query = { database.getPokemons() },
            fetch = { client.getPokemons() },
            cacheResponse = { pokemonEntities -> database.cachePokemons(pokemonEntities) },
            repositoryMapper = object : RepositoryMapper<PokemonDTO, RealmList<PokemonEntity>, List<Pokemon>?> {
                override fun dtoToDBEntity(dto: PokemonDTO): RealmList<PokemonEntity> = dto.results.toRealmList()

                override fun dbEntityToData(dbEntity: RealmList<PokemonEntity>): List<Pokemon> = dbEntity.toPokemonList()
            },
        )
    }

    override suspend fun getPokemonInfo(name: String): Flow<Resource<PokemonInfo?>> {
        return networkHandler(
            query = { database.getPokemonInfo(name) },
            fetch = { client.getPokemonInfo(name) },
            cacheResponse = { pokemonEntity -> database.cachePokemonInfo(pokemonEntity) },
            repositoryMapper = object : RepositoryMapper<PokemonInfoDTO, PokemonEntity, PokemonInfo?> {
                override fun dtoToDBEntity(dto: PokemonInfoDTO): PokemonEntity = dto.toPokemonRealm()

                override fun dbEntityToData(dbEntity: PokemonEntity): PokemonInfo = dbEntity.toPokemonInfo()
            },
        )
    }
}
