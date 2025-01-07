package com.al4apps.binlib.di

import com.al4apps.binlib.data.network.BinListApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<BinListApi> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(BinListApi::class.java)
    }

}