package com.zaklabs.pokey.core.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Pokemon DTO
 *
 * @property results
 * @constructor Create empty Pokemon DTO
 */
@Serializable
data class PokemonDTO(@SerialName("results") val results: List<ResultDTO>) {

    /**
     * Result DTO
     *
     * @property name
     * @constructor Create empty Result DTO
     */
    @Serializable
    data class ResultDTO(
        @SerialName("name") val name: String,
        @SerialName("url") val url: String,
    ) {
        val id: Int
            inline get() = url.split("/".toRegex()).dropLast(1).last().toInt()

        val imageUrl: String
            inline get() = "$IMAGE_URL/${url.split("/".toRegex()).dropLast(1).last()}.png"

        companion object {
            const val IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
        }
    }
}
