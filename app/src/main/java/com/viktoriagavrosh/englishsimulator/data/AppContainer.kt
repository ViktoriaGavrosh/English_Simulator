package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.RepeatRepository
import com.viktoriagavrosh.englishsimulator.data.database.RepeatScreenRepository

/**
 * App container for Dependency injection
 */
interface AppContainer {
    val repeatRepository: RepeatRepository
}

/**
 * [AppContainer] implementation that provides instances of [RepeatScreenRepository]
 */
internal class DefaultAppContainer(database: AppDatabase) : AppContainer {
    override val repeatRepository: RepeatRepository by lazy {
        RepeatScreenRepository(database)
    }
}
