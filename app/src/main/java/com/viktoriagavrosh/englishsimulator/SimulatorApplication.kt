package com.viktoriagavrosh.englishsimulator

import android.app.Application
import com.viktoriagavrosh.englishsimulator.di.dbModule
import com.viktoriagavrosh.englishsimulator.di.repositoriesModule
import com.viktoriagavrosh.englishsimulator.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SimulatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SimulatorApplication)
            modules(dbModule, repositoriesModule, viewModelsModule)
        }
    }
}
