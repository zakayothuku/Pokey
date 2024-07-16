package com.zaklabs.pokey.core.model.mapper

import com.zaklabs.pokey.core.model.dto.PokemonDTO
import com.zaklabs.pokey.core.model.dto.PokemonInfoDTO
import com.zaklabs.pokey.core.model.entity.PokemonAbilityEntity
import com.zaklabs.pokey.core.model.entity.PokemonEntity
import com.zaklabs.pokey.core.model.entity.PokemonStatEntity
import com.zaklabs.pokey.core.model.uimodel.PokemonInfo
import com.zaklabs.pokey.core.model.uimodel.Pokemon
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList

object Mappers {
    /**
     * To realm list
     *
     * @return RealmList<PokemonEntity>
     */
    fun List<PokemonDTO.ResultDTO>.toRealmList(): RealmList<PokemonEntity> {
        val realmList = realmListOf<PokemonEntity>()
        map { dto ->
            realmList.add(
                PokemonEntity().apply {
                    id = dto.id
                    name = dto.name
                    imageUrl = dto.imageUrl
                },
            )
        }
        return realmList
    }

    /**
     * To pokemon list
     *
     * @return List<Pokemon>
     */
    fun RealmList<PokemonEntity>.toPokemonList(): List<Pokemon> {
        val modelList = mutableListOf<Pokemon>()
        map { realm ->
            modelList.add(
                Pokemon(
                    id = realm.id,
                    name = realm.name,
                    imageUrl = realm.imageUrl,
                ),
            )
        }
        return modelList
    }

    /**
     * To pokemon realm
     *
     * @return PokemonEntity
     */
    fun PokemonInfoDTO.toPokemonRealm(): PokemonEntity =
        PokemonEntity().apply {
            id = this@toPokemonRealm.id
            name = this@toPokemonRealm.name
            order = this@toPokemonRealm.order
            weight = this@toPokemonRealm.weight
            height = this@toPokemonRealm.height
            imageUrl = this@toPokemonRealm.sprites.other.officialArtwork.frontDefault
            baseExperience = this@toPokemonRealm.baseExperience
            abilities = this@toPokemonRealm.abilities.toPokemonAbilityRealmList()
            stats = this@toPokemonRealm.stats.toPokemonStatRealmList()
        }

    /**
     * To pokemon ability realm list
     *
     * @return RealmList<PokemonAbilityEntity>
     */
    private fun List<PokemonInfoDTO.AbilityDTO>.toPokemonAbilityRealmList(): RealmList<PokemonAbilityEntity> {
        val realmList = realmListOf<PokemonAbilityEntity>()
        map { dto ->
            realmList.add(
                PokemonAbilityEntity().apply {
                    name = dto.ability.name
                    isHidden = dto.isHidden
                    order = dto.slot
                },
            )
        }
        return realmList
    }

    /**
     * To pokemon stat realm list
     *
     * @return RealmList<PokemonStatEntity>
     */
    private fun List<PokemonInfoDTO.StatDTO>.toPokemonStatRealmList(): RealmList<PokemonStatEntity> {
        val realmList = realmListOf<PokemonStatEntity>()

        map { dto ->
            realmList.add(
                PokemonStatEntity().apply {
                    name = dto.stat.name
                    baseStat = dto.baseStat
                    effort = dto.effort
                },
            )
        }
        return realmList
    }

    /**
     * To pokemon info
     *
     * @return
     */
    fun PokemonEntity.toPokemonInfo(): PokemonInfo =
        PokemonInfo(
            id = id,
            name = name,
            order = order,
            weight = weight,
            height = height,
            imageUrl = imageUrl,
            baseExperience = baseExperience,
            abilities = abilities.toPokemonAbilityList(),
            stats = stats.toPokemonStatList(),
        )

    /**
     * To pokemon ability list
     *
     * @return
     */
    private fun RealmList<PokemonAbilityEntity>?.toPokemonAbilityList(): List<PokemonInfo.PokemonAbility> {
        val list = mutableListOf<PokemonInfo.PokemonAbility>()
        this?.map { realm ->
            list.add(
                PokemonInfo.PokemonAbility(
                    name = realm.name,
                    isHidden = realm.isHidden,
                    order = realm.order,
                ),
            )
        } ?: emptyList()
        return list
    }

    /**
     * To pokemon stat list
     *
     * @return
     */
    private fun RealmList<PokemonStatEntity>?.toPokemonStatList(): List<PokemonInfo.PokemonStat> {
        val list = mutableListOf<PokemonInfo.PokemonStat>()
        this?.map { realm ->
            list.add(
                PokemonInfo.PokemonStat(
                    name = realm.name,
                    baseStat = realm.baseStat,
                ),
            )
        } ?: emptyList()
        return list
    }
}
