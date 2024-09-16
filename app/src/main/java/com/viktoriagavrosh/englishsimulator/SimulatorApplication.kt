package com.viktoriagavrosh.englishsimulator

import android.app.Application
import com.viktoriagavrosh.englishsimulator.data.AppContainer
import com.viktoriagavrosh.englishsimulator.data.DefaultAppContainer
import com.viktoriagavrosh.englishsimulator.data.database.getDatabase

class SimulatorApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(getDatabase(this))
    }
}
