package com.zaklabs.pokey.core.network.api

import com.zaklabs.pokey.core.model.dto.PokemonDTO
import com.zaklabs.pokey.core.model.dto.PokemonInfoDTO
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class PokeyApiClientTest {

    @Mock
    private lateinit var service: IPokeyApiService

    private lateinit var pokeyApiClient: PokeyApiClient

    /**
     * Setup
     *
     */
    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        pokeyApiClient = PokeyApiClient(service)
    }

    /**
     * Get pokemons returns successful response
     *
     */
    @Test
    fun `getPokemons returns successful response`() = runBlocking {
        val mockResponse = Response.success(
            PokemonDTO(
                results = listOf(
                    PokemonDTO.ResultDTO(
                        name = "pikachu",
                        url = "https://pokeapi.co/api/v2/pokemon/1/"
                    ),
                    PokemonDTO.ResultDTO(
                        name = "bulbasaur",
                        url = "https://pokeapi.co/api/v2/pokemon/2/"
                    )
                )
            )
        )
        whenever(service.getPokemons()).thenReturn(mockResponse)

        val result = pokeyApiClient.getPokemons()

        assert(result.isSuccessful)
        assert(result.body()?.results?.size == 2)
        assert(result.body()?.results?.get(0)?.name == "pikachu")
        assert(result.body()?.results?.get(1)?.name == "bulbasaur")
    }

    /**
     * Get pokemons returns error response
     *
     */
    @Test
    fun `getPokemons returns error response`(): Unit = runBlocking {
        whenever(service.getPokemons()).thenReturn(Response.error(404, "".toResponseBody(null)))
        val result = pokeyApiClient.getPokemons()
        assert(!result.isSuccessful)
        assert(result.code() == 404)
    }

    /**
     * Get pokemon info returns successful response
     *
     */
    @Test
    fun `getPokemonInfo returns successful response`() = runBlocking {
        val pokemonName = "pikachu"
        val mockResponse = Response.success(
            PokemonInfoDTO(
                id = 1,
                name = "pikachu",
                order = 7199,
                weight = 6198,
                height = 5431,
                baseExperience = 5991,
                abilities = listOf(
                    PokemonInfoDTO.AbilityDTO(
                        ability = PokemonInfoDTO.AbilityDTO.AbilityDTO(
                            name = "static",
                            url = "https://pokeapi.co/api/v2/ability/1/"
                        ),
                        isHidden = false,
                        slot = 1
                    )
                ),
                stats = listOf(
                    PokemonInfoDTO.StatDTO(
                        baseStat = 51,
                        effort = 0,
                        stat = PokemonInfoDTO.StatDTO.StatDTO(
                            name = "hp",
                            url = "https://pokeapi.co/api/v2/stat/1/"
                        )
                    )
                ),
                sprites = PokemonInfoDTO.SpritesDTO(
                    other = PokemonInfoDTO.SpritesDTO.OtherDTO(
                        officialArtwork = PokemonInfoDTO.SpritesDTO.OtherDTO.OfficialArtworkDTO(
                            frontDefault = "reque"
                        )
                    )
                ),
            )
        )
        whenever(service.getPokemonInfo(pokemonName)).thenReturn(mockResponse)

        val result = pokeyApiClient.getPokemonInfo(pokemonName)

        assert(result.isSuccessful)
        assert(result.body()?.id == 1)
        assert(result.body()?.name == "pikachu")
    }

    /**
     * Get pokemon info returns error response
     *
     */
    @Test
    fun `getPokemonInfo returns error response`() = runBlocking {
        val pokemonName = "pikachu"
        whenever(service.getPokemonInfo(pokemonName)).thenReturn(Response.error(404, "".toResponseBody(null)))
        val result = pokeyApiClient.getPokemonInfo(pokemonName)
        assert(!result.isSuccessful)
    }
}