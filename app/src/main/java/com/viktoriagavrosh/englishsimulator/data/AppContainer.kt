package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppRoomDatabase
/*
/**
 * App container for Dependency injection. Holds objects of all repositories.
 */
interface AppContainer {
    val translateRepository: TranslateRepository
    val issueRepository: IssueRepository
}

/**
 *  implementation that provides instances of repositories
 *
 * @param database instance of [AppRoomDatabase]
 * @return instance of [AppContainer]
 */
internal class DefaultAppContainer(database: AppRoomDatabase) : AppContainer {
    override val translateRepository: TranslateRepository by lazy {
        RepeatScreenRepository(database)
    }

    override val issueRepository: IssueRepository by lazy {
        LocalIssueRepository(database)
    }
}


 */
