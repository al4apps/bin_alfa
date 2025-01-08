package com.al4apps.binlib.app

import android.app.Application
import com.al4apps.binlib.di.dataModule
import com.al4apps.binlib.di.domainModule
import com.al4apps.binlib.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, dataModule, domainModule)
        }

    }
}