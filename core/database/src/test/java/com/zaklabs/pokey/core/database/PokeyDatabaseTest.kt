package com.zaklabs.pokey.core.database

import com.zaklabs.pokey.core.model.entity.PokemonAbilityEntity
import com.zaklabs.pokey.core.model.entity.PokemonEntity
import com.zaklabs.pokey.core.model.entity.PokemonStatEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PokeyDatabaseTest {

    private lateinit var realm: Realm
    private lateinit var pokeyDatabase: PokeyDatabase

    @BeforeEach
    fun setup() {
        val config = RealmConfiguration
            .Builder(
                schema = setOf(
                    PokemonEntity::class,
                    PokemonAbilityEntity::class,
                    PokemonStatEntity::class
                )
            )
            .inMemory()
            .name("test-realm")
            .build()

        realm = Realm.open(config)
        pokeyDatabase = PokeyDatabase(realm)
    }

    @AfterEach
    fun tearDown() {
        realm.close()
    }

    /**
     * Cache pokemons saves pokemons to database
     *
     */
    @Test
    fun `cachePokemons saves pokemons to database`() = runBlocking {
        val pokemon1 = PokemonEntity().apply { id = 1; name = "Bulbasaur" }
        val pokemon2 = PokemonEntity().apply { id = 2; name = "Ivysaur" }
        val pokemons = realmListOf(pokemon1, pokemon2)

        pokeyDatabase.cachePokemons(pokemons)

        val savedPokemons = realm.query<PokemonEntity>().find()
        assertEquals(2, savedPokemons.size)
        assertEquals("Bulbasaur", savedPokemons[0].name)
        assertEquals("Ivysaur", savedPokemons[1].name)
    }

    /**
     * Get pokemons returns all cached pokemons
     *
     */
    @Test
    fun `getPokemons returns all cached pokemons`() = runBlocking {
        realm.write {
            copyToRealm(PokemonEntity().apply { id = 1; name = "Charmander" })
            copyToRealm(PokemonEntity().apply { id = 2; name = "Charmeleon" })
        }

        val pokemons = pokeyDatabase.getPokemons().first()

        assertEquals(2, pokemons.size)
        assertEquals("Charmander", pokemons[0].name)
        assertEquals("Charmeleon", pokemons[1].name)
    }

    /**
     * Cache pokemon info updates existing pokemon or creates new one
     *
     */
    @Test
    fun `cachePokemonInfo updates existing pokemon or creates new one`() = runBlocking {
        realm.write {
            copyToRealm(PokemonEntity().apply {
                id = 1
                name = "Pikachu"
                weight = 60
            })
        }

        val updatedPokemon = PokemonEntity().apply {
            id = 1
            name = "Pikachu"
            weight = 65
        }
        pokeyDatabase.cachePokemonInfo(updatedPokemon)

        val savedPokemon = realm.query<PokemonEntity>("id == 1").find().firstOrNull()
        assertNotNull(savedPokemon)
        assertEquals("Pikachu", savedPokemon?.name)
        assertEquals(65, savedPokemon?.weight)

        val newPokemon = PokemonEntity().apply {
            id = 2
            name = "Squirtle"
            weight = 90
        }
        pokeyDatabase.cachePokemonInfo(newPokemon)

        val savedNewPokemon = realm.query<PokemonEntity>("id == 2").find().firstOrNull()
        assertNotNull(savedNewPokemon)
        assertEquals("Squirtle", savedNewPokemon?.name)
        assertEquals(90, savedNewPokemon?.weight)
    }

    /**
     * Get pokemon info returns pokemon by name
     *
     */
    @Test
    fun `getPokemonInfo returns pokemon by name`() = runBlocking {
        realm.write {
            copyToRealm(PokemonEntity().apply { id = 1; name = "Venusaur" })
        }

        val pokemon = pokeyDatabase.getPokemonInfo("Venusaur").first()

        assertNotNull(pokemon)
        assertEquals("Venusaur", pokemon.name)
    }
}