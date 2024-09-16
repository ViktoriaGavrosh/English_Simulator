package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toSentence
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.internal.NopCollector.emit
import kotlinx.coroutines.flow.map

interface RepeatRepository {
    /**
     * Retrieve all items from given data source
     */
    fun getAllSentences(): Flow<RequestResult<List<Sentence>>>
}

internal class RepeatScreenRepository(
    private val database: AppDatabase
) : RepeatRepository {

    /**
     * Retrieve all [Sentence] from database
     */
    override fun getAllSentences(): Flow<RequestResult<List<Sentence>>> {
        return database.sentenceDao().getAllSentences()
            .map { list ->
                list.map { it.toSentence() }
            }
            .map<List<Sentence>, RequestResult<List<Sentence>>> { RequestResult.Success(it) }
            .catch { emit(RequestResult.Error(it)) }   // TODO check here!!!
    }
}
