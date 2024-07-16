package com.zaklabs.pokey.core.model.uimodel

import kotlinx.serialization.Serializable

/**
 * Pokemon info
 *
 * @property id
 * @property name
 * @property order
 * @property weight
 * @property height
 * @property imageUrl
 * @property baseExperience
 * @property abilities
 * @property stats
 * @constructor Create empty Pokemon info
 */
@Serializable
data class PokemonInfo(
    val id: Int?,
    val name: String?,
    val order: Int?,
    val weight: Int?,
    val height: Int?,
    val imageUrl: String?,
    val baseExperience: Int?,
    val abilities: List<PokemonAbility>,
    val stats: List<PokemonStat>,
) {

    /**
     * Pokemon ability
     *
     * @property name
     * @property order
     * @property isHidden
     * @constructor Create empty Pokemon ability
     */
    @Serializable
    data class PokemonAbility(
        val name: String,
        val order: Int?,
        val isHidden: Boolean,
    )

    /**
     * Pokemon stat
     *
     * @property name
     * @property baseStat
     * @constructor Create empty Pokemon stat
     */
    @Serializable
    data class PokemonStat(
        val name: String,
        val baseStat: Int?,
    ) {
        val displayName: String
            get() = when (name) {
                "hp" -> "HP"
                "attack" -> "ATK"
                "defense" -> "DEF"
                "speed" -> "SPD"
                "special-attack" -> "SPATK"
                "special-defense" -> "SPDEF"
                else -> name
            }
    }
}
