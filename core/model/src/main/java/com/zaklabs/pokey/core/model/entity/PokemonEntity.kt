package com.zaklabs.pokey.core.model.entity

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Pokemon realm
 *
 * @constructor Create empty Pokemon realm
 */
class PokemonEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var order: Int = 0
    var weight: Int = 0
    var height: Int = 0
    var imageUrl: String = ""
    var baseExperience: Int = 0
    var abilities: RealmList<PokemonAbilityEntity>? = realmListOf()
    var stats: RealmList<PokemonStatEntity>? = realmListOf()
}
