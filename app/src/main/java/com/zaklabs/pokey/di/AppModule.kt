package com.zaklabs.pokey.di

import com.zaklabs.pokey.core.data.di.dataModule
import com.zaklabs.pokey.ui.screens.home.HomeViewModel
import com.zaklabs.pokey.ui.screens.info.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    dataModule()
    viewModel { HomeViewModel(get()) }
    viewModel { InfoViewModel(get(), get()) }
}
