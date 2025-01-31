package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toUiItem
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
     * @return flow of [RequestResult] with list [UiItem]
     */
    fun getAllItems(): Flow<RequestResult<List<UiItem>>>

    /**
     * Retrieve all items from given data source by theme
     *
     * @param theme theme of items
     * @return flow of [RequestResult] with list [UiItem]
     */
    fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<UiItem>>>

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
     * Retrieve all [UiItem] from database
     *
     * @return flow of [RequestResult] with list [UiItem]
     */
    override fun getAllItems(): Flow<RequestResult<List<UiItem>>> {
        return try {
            database.issueDao().getAllIssues()
                .map { list ->
                    list.map { it.toUiItem() }
                }
                .map<List<UiItem>, RequestResult<List<UiItem>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }

    /**
     * Retrieve all [UiItem] from database by theme
     *
     * @param theme theme of issues
     * @return flow of [RequestResult] with list [UiItem]
     */
    override fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<UiItem>>> {
        return try {
            database.issueDao()
                .getAllIssuesByTheme(theme = theme)
                .map { list ->
                    list.map { it.toUiItem() }
                }
                .map<List<UiItem>, RequestResult<List<UiItem>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
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
