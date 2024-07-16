package com.zaklabs.pokey.core.model.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

/**
 * Pokemon stat realm
 *
 * @constructor Create empty Pokemon stat realm
 */
class PokemonStatEntity : RealmObject {
    @PrimaryKey
    var id: ObjectId = BsonObjectId()
    var name: String = ""
    var baseStat: Int = 0
    var effort: Int = 0
}
