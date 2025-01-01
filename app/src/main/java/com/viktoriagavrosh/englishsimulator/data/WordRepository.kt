package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.Word
import com.viktoriagavrosh.englishsimulator.model.WordDb
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toWord
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
     * will insert element into given data source
     *
     * @param wordDb object [WordDb] that will be insert
     */
    suspend fun insert(wordDb: WordDb)
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
     * will insert element into database
     *
     * @param wordDb object [WordDb] that will be insert
     */
    override suspend fun insert(wordDb: WordDb) {
        database.wordDao().insert(wordDb)
    }
}
