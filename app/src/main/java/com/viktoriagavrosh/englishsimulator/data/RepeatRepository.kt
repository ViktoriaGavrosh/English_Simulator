package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toSentence
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * provide data for ui from data source
 */
interface RepeatRepository {

    /**
     * Retrieve all items from given data source
     *
     * @return flow of [RequestResult] with list [Sentence]
     */
    fun getAllSentences(): Flow<RequestResult<List<Sentence>>>
}

/**
 * provide data for ui (RepeatScreen) from local database
 *
 * @param database instance of local database
 */
internal class RepeatScreenRepository(
    private val database: AppDatabase
) : RepeatRepository {

    /**
     * Retrieve all [Sentence] from database
     *
     * @return flow of [RequestResult] with list [Sentence]
     */
    override fun getAllSentences(): Flow<RequestResult<List<Sentence>>> {
        return try {
            database.sentenceDao().getAllSentences()
                .map { list ->
                    list.map { it.toSentence() }
                }
                .map<List<Sentence>, RequestResult<List<Sentence>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }
}
