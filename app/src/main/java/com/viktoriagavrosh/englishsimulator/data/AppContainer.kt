package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppRoomDatabase

/**
 * App container for Dependency injection. Holds objects of all repositories.
 */
interface AppContainer {
    val repeatRepository: TranslateRepository
}

/**
 *  implementation that provides instances of repositories
 *
 * @param database instance of [AppRoomDatabase]
 * @return instance of [AppContainer]
 */
internal class DefaultAppContainer(database: AppRoomDatabase) : AppContainer {
    override val repeatRepository: TranslateRepository by lazy {
        RepeatScreenRepository(database)
    }
}
