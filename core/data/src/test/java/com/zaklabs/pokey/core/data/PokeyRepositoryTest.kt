package com.zaklabs.pokey.core.data

import com.zaklabs.pokey.core.database.IPokeyDatabase
import com.zaklabs.pokey.core.model.dto.PokemonDTO
import com.zaklabs.pokey.core.model.dto.PokemonInfoDTO
import com.zaklabs.pokey.core.model.entity.PokemonEntity
import com.zaklabs.pokey.core.model.mapper.Mappers.toPokemonInfo
import com.zaklabs.pokey.core.model.resource.ErrorType
import com.zaklabs.pokey.core.model.resource.Resource
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import com.zaklabs.pokey.core.network.api.IPokeyApiClient
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

class PokeyRepositoryTest {

    private lateinit var repository: PokeyRepository
    private lateinit var mockDatabase: IPokeyDatabase
    private lateinit var mockClient: IPokeyApiClient

    @BeforeEach
    fun setup() {
        mockDatabase = mock(IPokeyDatabase::class.java)
        mockClient = mock(IPokeyApiClient::class.java)
        repository = PokeyRepository(mockDatabase, mockClient)
    }

    @Test
    fun `initial state is Resource Loading`() = runTest {
        `when`(mockDatabase.getPokemons()).thenReturn(flowOf(realmListOf()))

        val result = repository.getPokemons().first()

        Assertions.assertTrue(result is Resource.Loading)
    }

    @Test
    fun `getPokemons returns success from network`() = runTest {
        val pokemonDTO = PokemonDTO(
            listOf(
                PokemonDTO.ResultDTO(
                    name = "bulbasaur",
                    url = "https://pokeapi.co/api/v2/pokemon/1/"
                )
            )
        )
        val pokemonEntity = realmListOf<PokemonEntity>().apply {
            add(PokemonEntity().apply {
                id = 1
                name = "bulbasaur"
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            })
        }
        val pokemonList = listOf(
            Pokemon(
                id = 1,
                name = "bulbasaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            )
        )

        `when`(mockClient.getPokemons()).thenReturn(Response.success(pokemonDTO))
        whenever(mockDatabase.cachePokemons(pokemonEntity)).thenReturn(Unit)
        `when`(mockDatabase.getPokemons()).thenReturn(flowOf(pokemonEntity))

        val result = repository.getPokemons().drop(1).first() // Drop the initial Loading state

        Assertions.assertTrue(result is Resource.Success)
        Assertions.assertEquals(pokemonList, (result as Resource.Success).data)
    }

    @Test
    fun `getPokemons returns success from database with error from network`() = runTest {
        val pokemonEntity = realmListOf<PokemonEntity>().apply {
            add(
                PokemonEntity().apply {
                    id = 2
                    name = "pikachu"
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
                }
            )
        }
        val pokemonList = listOf(
            Pokemon(
                id = 2,
                name = "pikachu",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
            )
        )

        `when`(mockDatabase.getPokemons()).thenReturn(flowOf(pokemonEntity))

        val result = repository.getPokemons().drop(1).first() // Drop the initial Loading state

        Assertions.assertTrue(result is Resource.Error)
        Assertions.assertEquals(pokemonList, (result as Resource.Error).data)
    }

    @Test
    fun `getPokemons returns error from network`() = runTest {
        `when`(mockClient.getPokemons()).thenReturn(Response.error(500, "".toResponseBody(null)))
        `when`(mockDatabase.getPokemons()).thenReturn(flowOf(realmListOf()))

        val result = repository.getPokemons().drop(1).first() // Drop the initial Loading state

        Assertions.assertTrue(result is Resource.Error)
        Assertions.assertEquals(ErrorType.Network.ServerError, (result as Resource.Error).error)
    }

    @Test
    fun `getPokemonInfo returns success from network`() = runTest {
        val pokemonName = "ivysaur"
        val pokemonInfoDTO = PokemonInfoDTO(
            id = 2,
            name = pokemonName,
            abilities = emptyList(),
            stats = emptyList(),
            sprites = PokemonInfoDTO.SpritesDTO(),
            baseExperience = 221,
            height = 110,
            order = 2,
            weight = 220
        )
        val pokemonEntity = PokemonEntity().apply {
            id = pokemonInfoDTO.id
            name = pokemonInfoDTO.name
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
            weight = pokemonInfoDTO.weight
            height = pokemonInfoDTO.height
            baseExperience = pokemonInfoDTO.baseExperience
            abilities = realmListOf()
            stats = realmListOf()
        }
        val pokemonInfo = pokemonEntity.toPokemonInfo()

        `when`(mockClient.getPokemonInfo(pokemonName)).thenReturn(Response.success(pokemonInfoDTO))
        whenever(mockDatabase.cachePokemonInfo(pokemonEntity)).thenReturn(Unit)
        `when`(mockDatabase.getPokemonInfo(pokemonName)).thenReturn(flowOf(pokemonEntity))

        val result = repository.getPokemonInfo(pokemonName).drop(1).first() // Drop the initial Loading state

        Assertions.assertTrue(result is Resource.Success)
        Assertions.assertEquals(pokemonInfo, (result as Resource.Success).data)

        verify(mockClient).getPokemonInfo(pokemonName)
    }

    @Test
    fun `getPokemonInfo returns success from database with error from network`() = runTest {
        val pokemonName = "porygon"
        val pokemonEntity = PokemonEntity().apply {
            id = 3
            name = pokemonName
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
            weight = 100
            height = 100
            baseExperience = 100
            abilities = realmListOf()
            stats = realmListOf()
        }
        val pokemonInfo = pokemonEntity.toPokemonInfo()

        `when`(mockDatabase.getPokemonInfo(pokemonName)).thenReturn(flowOf(pokemonEntity))

        val result = repository.getPokemonInfo(pokemonName).drop(1).first() // Drop the initial Loading state

        Assertions.assertTrue(result is Resource.Error)
        Assertions.assertEquals(pokemonInfo, (result as Resource.Error).data)
    }

    @Test
    fun `getPokemonInfo returns error from network`() = runTest {
        val pokemonName = "articuno"
        `when`(mockClient.getPokemonInfo(pokemonName)).thenReturn(Response.error(404, "".toResponseBody(null)))
        `when`(mockDatabase.getPokemonInfo(pokemonName)).thenReturn(flowOf(null))

        val result = repository.getPokemonInfo(pokemonName).drop(1).first() // Drop the initial Loading state

        Assertions.assertTrue(result is Resource.Error)
        Assertions.assertEquals(ErrorType.Network.NotFound, (result as Resource.Error).error)
    }
}