package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.Word
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toWord
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
     * @return flow of [RequestResult] with list [Word]
     */
    fun getAllWords(): Flow<RequestResult<List<Word>>>

    /**
     * Retrieve all items from given data source by theme
     *
     * @param theme theme of items
     * @return flow of [RequestResult] with list [Word]
     */
    fun getAllWordsByTheme(theme: String): Flow<RequestResult<List<Word>>>

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
     * @return flow of [RequestResult] with [Word]
     */
    fun getWordById(id: Int): Flow<RequestResult<Word>>

    /**
     * will insert element into given data source
     *
     * @param word object [Word] that will be insert
     */
    suspend fun insert(word: Word)

    /**
     * will update element into given data source
     *
     * @param word object [Word] that will be update
     */
    suspend fun update(word: Word)

    /**
     * will delete element from given data source
     *
     * @param word object [Word] that will be delete
     */
    suspend fun delete(word: Word)
}

/**
 * provide data for ui from local database
 *
 * @param database instance of local database
 */
internal class LocalWordRepository(
    private val database: AppDatabase
) : WordRepository {
    /**
     * Retrieve all [Word] from database
     *
     * @return flow of [RequestResult] with list [Word]
     */
    override fun getAllWords(): Flow<RequestResult<List<Word>>> {
        return try {
            database.wordDao().getAllWords()
                .map { list ->
                    list.map { it.toWord() }
                }
                .map<List<Word>, RequestResult<List<Word>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }

    /**
     * Retrieve all [Word] from database by theme
     *
     * @param theme theme of words
     * @return flow of [RequestResult] with list [Word]
     */
    override fun getAllWordsByTheme(theme: String): Flow<RequestResult<List<Word>>> {
        return try {
            database.wordDao()
                .getAllWordsByTheme(theme = theme)
                .map { list ->
                    list.map { it.toWord() }
                }
                .map<List<Word>, RequestResult<List<Word>>> { RequestResult.Success(it) }
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
     * @return flow of [RequestResult] with [Word]
     */
    override fun getWordById(id: Int): Flow<RequestResult<Word>> {
        return try {
            database.wordDao()
                .getWordById(id = id)
                .map { RequestResult.Success(it.toWord()) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }

    /**
     * will insert element into database
     *
     * @param word object [Word] that will be insert
     */
    override suspend fun insert(word: Word) {
        database.wordDao().insert(word.toWordDb())
    }

    /**
     * will update element into database
     *
     * @param word object [Word] that will be update
     */
    override suspend fun update(word: Word) {
        database.wordDao().update(word.toWordDb())
    }

    /**
     * will delete element from given data source
     *
     * @param word object [Word] that will be delete
     */
    override suspend fun delete(word: Word) {
        database.wordDao().delete(word.toWordDb())
    }
}
