package com.zaklabs.pokey.core.database.di

import com.zaklabs.pokey.core.database.IPokeyDatabase
import com.zaklabs.pokey.core.database.PokeyDatabase
import com.zaklabs.pokey.core.model.entity.PokemonAbilityEntity
import com.zaklabs.pokey.core.model.entity.PokemonEntity
import com.zaklabs.pokey.core.model.entity.PokemonStatEntity
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.Module

fun Module.databaseModule() {
    single<Configuration> {
        RealmConfiguration.Builder(
            schema = setOf(
                PokemonEntity::class,
                PokemonAbilityEntity::class,
                PokemonStatEntity::class,
            ),
        ).name("pokey.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
    }
    single {
        Realm.open(get<Configuration>())
    }
    single<IPokeyDatabase> {
        PokeyDatabase(get())
    }
}
