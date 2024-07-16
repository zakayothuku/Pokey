package com.zaklabs.pokey.core.data.di

import com.zaklabs.pokey.core.data.IPokeyRepository
import com.zaklabs.pokey.core.data.PokeyRepository
import com.zaklabs.pokey.core.database.di.databaseModule
import com.zaklabs.pokey.core.network.di.networkModule
import org.koin.core.module.Module

fun Module.dataModule() {
    networkModule()
    databaseModule()
    single<IPokeyRepository> { PokeyRepository(get(), get()) }
}
