package com.zaklabs.pokey.core.network.di

import com.zaklabs.pokey.core.network.BuildConfig
import com.zaklabs.pokey.core.network.Constants
import com.zaklabs.pokey.core.network.api.IPokeyApiClient
import com.zaklabs.pokey.core.network.api.IPokeyApiService
import com.zaklabs.pokey.core.network.api.PokeyApiClient
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

fun Module.networkModule() {
    single {
        HttpLoggingInterceptor().apply {
            setLevel(
                when {
                    BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                    else -> HttpLoggingInterceptor.Level.NONE
                },
            )
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(json.asConverterFactory(Constants.MEDIA_TYPE.toMediaTypeOrNull()!!))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(IPokeyApiService::class.java)
    }

    single<IPokeyApiClient> {
        PokeyApiClient(get())
    }
}
