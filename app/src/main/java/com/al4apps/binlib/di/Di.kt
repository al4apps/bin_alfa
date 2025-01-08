package com.al4apps.binlib.di

import androidx.room.Room
import com.al4apps.binlib.data.datasource.DbSource
import com.al4apps.binlib.data.datasource.NetworkDataSource
import com.al4apps.binlib.data.datasource.TestDataSource
import com.al4apps.binlib.data.db.AppDatabase
import com.al4apps.binlib.data.db.CardsDao
import com.al4apps.binlib.data.network.BinListApi
import com.al4apps.binlib.data.repositories.CardsRepositoryImpl
import com.al4apps.binlib.domain.usecases.FetchCardInfoUseCase
import com.al4apps.binlib.domain.usecases.FetchCardsFromDbUseCase
import com.al4apps.binlib.presentation.history.HistoryViewModel
import com.al4apps.binlib.presentation.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<BinListApi> {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(BinListApi::class.java)
    }
}

val dataModule = module {

    single<CardsDao> {
        get<AppDatabase>().cardsDao()
    }

    single<TestDataSource> {
        TestDataSource()
    }

    single<NetworkDataSource> {
        NetworkDataSource(binListApi = get())
    }

    single<DbSource> { DbSource(dao = get()) }

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single<CardsRepositoryImpl> {
        CardsRepositoryImpl(
            dataSource = get(),
            dbSource = get()
        )
    }
}

val domainModule = module {
    factory<FetchCardInfoUseCase> {
        FetchCardInfoUseCase(cardsRepository = get())
    }
    factory<FetchCardsFromDbUseCase> {
        FetchCardsFromDbUseCase(cardsRepository = get())
    }

    viewModel<HomeViewModel> { HomeViewModel(fetchCardInfoUseCase = get()) }
    viewModel<HistoryViewModel> { HistoryViewModel(fetchCardsFromDbUseCase = get()) }

}