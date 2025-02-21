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
interface TranslateRepository {

    /**
     * Retrieve all items from given data source
     *
     * @return flow of [RequestResult] with list [Question.Sentence]
     */
    fun getAllSentences(): Flow<RequestResult<List<Question.Sentence>>>
}

/**
 * provide data for ui from local database
 *
 * @param database instance of local database
 */
internal class LocalTranslateRepository(
    private val database: AppDatabase
) : TranslateRepository, GameRepository {

    /**
     * Retrieve all [Question.Sentence] from database
     *
     * @return flow of [RequestResult] with list [Question.Sentence]
     */
    override fun getAllSentences(): Flow<RequestResult<List<Question.Sentence>>> {
        return getRequestResultFlow(
            getFlow = database.sentenceDao()::getAllSentences,
            mapper = { it.toQuestion() as Question.Sentence }
        )
    }
}
