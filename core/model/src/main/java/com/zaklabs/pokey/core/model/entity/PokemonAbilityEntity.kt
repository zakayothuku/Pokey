package com.zaklabs.pokey.core.model.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

/**
 * Pokemon ability realm
 *
 * @constructor Create empty Pokemon ability realm
 */
class PokemonAbilityEntity : RealmObject {
    @PrimaryKey
    var id: ObjectId = BsonObjectId()
    var name: String = ""
    var isHidden: Boolean = false
    var order: Int = 0
}
