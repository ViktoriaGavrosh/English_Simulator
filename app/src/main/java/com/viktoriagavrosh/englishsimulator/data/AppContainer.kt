package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase

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
