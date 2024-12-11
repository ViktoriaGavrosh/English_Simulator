package com.viktoriagavrosh.englishsimulator

import android.app.Application
import com.viktoriagavrosh.englishsimulator.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SimulatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SimulatorApplication)
            modules(appModule)
        }
    }
}
