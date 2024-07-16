package com.zaklabs.pokey.core.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Pokemon info DTO
 *
 * @property id
 * @property name
 * @property order
 * @property weight
 * @property height
 * @property sprites
 * @property baseExperience
 * @property abilities
 * @property stats
 * @constructor Create empty Pokemon info DTO
 */
@Serializable
data class PokemonInfoDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("order") val order: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("height") val height: Int,
    @SerialName("sprites") val sprites: SpritesDTO,
    @SerialName("base_experience") val baseExperience: Int,
    @SerialName("abilities") val abilities: List<AbilityDTO>,
    @SerialName("stats") val stats: List<StatDTO>,
) {
    /**
     * Sprites
     *
     * @property other
     * @constructor Create empty Sprites
     */
    @Serializable
    data class SpritesDTO(@SerialName("other") val other: OtherDTO = OtherDTO()) {

        /**
         * Other
         *
         * @property officialArtwork
         * @constructor Create empty Other
         */
        @Serializable
        data class OtherDTO(@SerialName("official-artwork") val officialArtwork: OfficialArtworkDTO = OfficialArtworkDTO()) {

            /**
             * Official artwork
             *
             * @property frontDefault
             * @constructor Create empty Official artwork
             */
            @Serializable
            data class OfficialArtworkDTO(@SerialName("front_default") val frontDefault: String = "")
        }
    }

    /**
     * Ability
     *
     * @property ability
     * @property isHidden
     * @property slot
     * @constructor Create empty Ability
     */
    @Serializable
    data class AbilityDTO(
        @SerialName("ability") val ability: AbilityDTO,
        @SerialName("is_hidden") val isHidden: Boolean,
        @SerialName("slot") val slot: Int,
    ) {
        /**
         * Ability
         *
         * @property name
         * @property url
         * @constructor Create empty Ability
         */
        @Serializable
        data class AbilityDTO(
            @SerialName("name") val name: String,
            @SerialName("url") val url: String,
        )
    }

    /**
     * Stat
     *
     * @property baseStat
     * @property effort
     * @property stat
     * @constructor Create empty Stat
     */
    @Serializable
    data class StatDTO(
        @SerialName("base_stat") val baseStat: Int,
        @SerialName("effort") val effort: Int,
        @SerialName("stat") val stat: StatDTO,
    ) {
        /**
         * Stat
         *
         * @property name
         * @property url
         * @constructor Create empty Stat
         */
        @Serializable
        data class StatDTO(
            @SerialName("name") val name: String,
            @SerialName("url") val url: String,
        )
    }
}
