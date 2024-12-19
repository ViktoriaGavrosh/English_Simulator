package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.Issue
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toIssue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * provide data for ui from data source
 */
interface IssueRepository {

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
}

/**
 * provide data for ui from local database
 *
 * @param database instance of local database
 */
internal class LocalIssueRepository(
    private val database: AppDatabase
) : IssueRepository {

    /**
     * Retrieve all [Issue] from database
     *
     * @return flow of [RequestResult] with list [Issue]
     */
    override fun getAllIssue(): Flow<RequestResult<List<Issue>>> {
        return try {
            database.issueDao().getAllIssues()
                .map { list ->
                    list.map { it.toIssue() }
                }
                .map<List<Issue>, RequestResult<List<Issue>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }

    /**
     * Retrieve all [Issue] from database by theme
     *
     * @param theme theme of issues
     * @return flow of [RequestResult] with list [Issue]
     */
    override fun getAllIssueByTheme(theme: String): Flow<RequestResult<List<Issue>>> {
        val result = try {
            val a = database.issueDao()
            val b = a.getAllIssuesByTheme(theme = theme)
            val c = b.map { list ->
                list.map { it.toIssue() }
            }
            val d = c.map<List<Issue>, RequestResult<List<Issue>>> { RequestResult.Success(it) }
            d
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
        return result
    }

    /**
     * Retrieve all themes from given database
     *
     * @return flow of [RequestResult] with list [String]
     */
    override fun getAllThemes(): Flow<RequestResult<List<String>>> {
        return try {
            database.issueDao().getAllThemes()
                .map<List<String>, RequestResult<List<String>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }
}
