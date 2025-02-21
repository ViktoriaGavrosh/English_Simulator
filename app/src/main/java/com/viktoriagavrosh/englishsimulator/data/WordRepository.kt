package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.getRequestResultFlow
import com.viktoriagavrosh.englishsimulator.utils.toQuestion
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
     * @return flow of [RequestResult] with list [Question.Word]
     */
    fun getAllWords(): Flow<RequestResult<List<Question.Word>>>

    /**
     * Retrieve all items from given data source by theme
     *
     * @param theme theme of items
     * @return flow of [RequestResult] with list [Question.Word]
     */
    fun getAllWordsByTheme(theme: String): Flow<RequestResult<List<Question.Word>>>

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
     * @return flow of [RequestResult] with [Question.Word]
     */
    fun getWordById(id: Int): Flow<RequestResult<Question.Word>>

    /**
     * will insert element into given data source
     *
     * @param word object [Question.Word] that will be insert
     */
    suspend fun insertWord(word: Question.Word)

    /**
     * will update element into given data source
     *
     * @param word object [Question.Word] that will be update
     */
    suspend fun updateWord(word: Question.Word)

    /**
     * will delete element from given data source
     *
     * @param word object [Question.Word] that will be delete
     */
    suspend fun deleteWord(word: Question.Word)
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
     * Retrieve all [Question.Word] from database
     *
     * @return flow of [RequestResult] with list [Question.Word]
     */
    override fun getAllWords(): Flow<RequestResult<List<Question.Word>>> {
        return getRequestResultFlow(
            getFlow = database.wordDao()::getAllWords,
            mapper = { it.toQuestion() as Question.Word }
        )
    }

    /**
     * Retrieve all [Question.Word] from database by theme
     *
     * @param theme theme of words
     * @return flow of [RequestResult] with list [Question.Word]
     */
    override fun getAllWordsByTheme(theme: String): Flow<RequestResult<List<Question.Word>>> {
        return getRequestResultFlow(
            getFlow = { database.wordDao().getAllWordsByTheme(theme = theme) },
            mapper = { it.toQuestion() as Question.Word }
        )
    }

    /**
     * Retrieve all themes from given database
     *
     * @return flow of [RequestResult] with list [String]
     */
    override fun getAllThemes(): Flow<RequestResult<List<String>>> {
        return getRequestResultFlow(
            getFlow = database.wordDao()::getAllThemes,
            mapper = { it }
        )
    }

    /**
     * Retrieve item from given database by id
     *
     * @param id unique word id
     * @return flow of [RequestResult] with [Question.Word]
     */
    override fun getWordById(id: Int): Flow<RequestResult<Question.Word>> {
        return try {
            database.wordDao().getWordById(id = id)
                .map { RequestResult.Success(it.toQuestion() as Question.Word) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }

    /**
     * will insert element into database
     *
     * @param word object [Question.Word] that will be insert
     */
    override suspend fun insertWord(word: Question.Word) {
        database.wordDao().insert(word.toWordDb())
    }

    /**
     * will update element into database
     *
     * @param word object [Question.Word] that will be update
     */
    override suspend fun updateWord(word: Question.Word) {
        database.wordDao().update(word.toWordDb())
    }

    /**
     * will delete element from given data source
     *
     * @param word object [Question.Word] that will be delete
     */
    override suspend fun deleteWord(word: Question.Word) {
        database.wordDao().delete(word.toWordDb())
    }
}
