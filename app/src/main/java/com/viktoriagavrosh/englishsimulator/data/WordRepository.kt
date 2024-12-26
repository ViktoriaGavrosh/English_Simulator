package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase

/**
 * provide data for ui from data source
 */
interface WordRepository {
    /*
        /**
         * Retrieve all items from given data source
         *
         * @return flow of [RequestResult] with list [Issue]
         */
        fun getAllIssue(): Flow<RequestResult<List<Issue>>>

        /**
         * Retrieve all items from given data source by theme
         *
         * @param theme theme of items
         * @return flow of [RequestResult] with list [Issue]
         */
        fun getAllIssueByTheme(theme: String): Flow<RequestResult<List<Issue>>>

        /**
         * Retrieve all themes from given data source
         *
         * @return flow of [RequestResult] with list [String]
         */
        fun getAllThemes(): Flow<RequestResult<List<String>>>

     */
}

/**
 * provide data for ui from local database
 *
 * @param database instance of local database
 */
internal class LocalWordRepository(
    private val database: AppDatabase
) : WordRepository {

}
