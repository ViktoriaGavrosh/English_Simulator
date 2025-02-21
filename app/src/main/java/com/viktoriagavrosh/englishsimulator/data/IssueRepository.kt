package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.getRequestResultFlow
import com.viktoriagavrosh.englishsimulator.utils.toQuestion
import kotlinx.coroutines.flow.Flow

/**
 * provide data for ui from data source
 */
interface IssueRepository {

    /**
     * Retrieve all items from given data source
     *
     * @return flow of [RequestResult] with list [Question.Issue]
     */
    fun getAllIssues(): Flow<RequestResult<List<Question.Issue>>>

    /**
     * Retrieve all items from given data source by theme
     *
     * @param theme theme of items
     * @return flow of [RequestResult] with list [Question.Issue]
     */
    fun getAllIssuesByTheme(theme: String): Flow<RequestResult<List<Question.Issue>>>

    /**
     * Retrieve all themes from given data source
     *
     * @return flow of [RequestResult] with list [String]
     */
    fun getAllThemes(): Flow<RequestResult<List<String>>>
}

/**
 * provide data for ui from local database
 *
 * @param database instance of local database
 */
internal class LocalIssueRepository(
    private val database: AppDatabase
) : IssueRepository, GameRepository {

    /**
     * Retrieve all [Question.Issue] from database
     *
     * @return flow of [RequestResult] with list [Question.Issue]
     */
    override fun getAllIssues(): Flow<RequestResult<List<Question.Issue>>> {
        return getRequestResultFlow(
            getFlow = database.issueDao()::getAllIssues,
            mapper = { it.toQuestion() as Question.Issue }
        )
    }

    /**
     * Retrieve all [Question.Issue] from database by theme
     *
     * @param theme theme of issues
     * @return flow of [RequestResult] with list [Question.Issue]
     */
    override fun getAllIssuesByTheme(theme: String): Flow<RequestResult<List<Question.Issue>>> {
        return getRequestResultFlow(
            getFlow = { database.issueDao().getAllIssuesByTheme(theme = theme) },
            mapper = { it.toQuestion() as Question.Issue }
        )
    }

    /**
     * Retrieve all themes from given database
     *
     * @return flow of [RequestResult] with list [String]
     */
    override fun getAllThemes(): Flow<RequestResult<List<String>>> {
        return getRequestResultFlow(
            getFlow = database.issueDao()::getAllThemes,
            mapper = { it }
        )
    }
}
