package com.zaklabs.pokey.core.model.uimodel

import kotlinx.serialization.Serializable

/**
 * Pokemon UI model
 *
 * @property id
 * @property name
 * @property imageUrl
 * @constructor Create empty Pokemon UI model
 */
@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
)
