package com.zaklabs.pokey

import android.app.Application
import com.zaklabs.pokey.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@PokeyApp)
            modules(appModule)
        }
    }
}
