package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toUiItem
import com.viktoriagavrosh.englishsimulator.utils.toWordDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * provide data for ui from data source
 */
interface WordRepository {
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
     * Retrieve all themes from given database
     *
     * @return flow of [RequestResult] with list [String]
     */
    fun getAllThemes(): Flow<RequestResult<List<String>>>

    /**
     * Retrieve item from given data source by id
     *
     * @param id unique item id
     * @return flow of [RequestResult] with [UiItem]
     */
    fun getWordById(id: Int): Flow<RequestResult<UiItem>>

    /**
     * will insert element into given data source
     *
     * @param word object [UiItem] that will be insert
     */
    suspend fun insertWord(word: UiItem)

    /**
     * will update element into given data source
     *
     * @param word object [UiItem] that will be update
     */
    suspend fun updateWord(word: UiItem)

    /**
     * will delete element from given data source
     *
     * @param word object [UiItem] that will be delete
     */
    suspend fun deleteWord(word: UiItem)
}

/**
 * provide data for ui from local database
 *
 * @param database instance of local database
 */
internal class LocalWordRepository(
    private val database: AppDatabase
) : WordRepository, GameRepository {
    /**
     * Retrieve all [UiItem] from database
     *
     * @return flow of [RequestResult] with list [UiItem]
     */
    override fun getAllItems(): Flow<RequestResult<List<UiItem>>> {
        return try {
            database.wordDao().getAllWords()
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
     * @param theme theme of words
     * @return flow of [RequestResult] with list [UiItem]
     */
    override fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<UiItem>>> {
        return try {
            database.wordDao()
                .getAllWordsByTheme(theme = theme)
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
            database.wordDao().getAllThemes()
                .map<List<String>, RequestResult<List<String>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }

    /**
     * Retrieve item from given database by id
     *
     * @param id unique word id
     * @return flow of [RequestResult] with [UiItem]
     */
    override fun getWordById(id: Int): Flow<RequestResult<UiItem>> {
        return try {
            database.wordDao()
                .getWordById(id = id)
                .map { RequestResult.Success(it.toUiItem()) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }

    /**
     * will insert element into database
     *
     * @param word object [UiItem] that will be insert
     */
    override suspend fun insertWord(word: UiItem) {
        database.wordDao().insert(word.toWordDb())
    }

    /**
     * will update element into database
     *
     * @param word object [UiItem] that will be update
     */
    override suspend fun updateWord(word: UiItem) {
        database.wordDao().update(word.toWordDb())
    }

    /**
     * will delete element from given data source
     *
     * @param word object [UiItem] that will be delete
     */
    override suspend fun deleteWord(word: UiItem) {
        database.wordDao().delete(word.toWordDb())
    }
}
