package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTranslateRepository(
    private val requestResult: RequestResult<List<Question.Sentence>>
) : TranslateRepository, GameRepository {

    override fun getAllSentences(): Flow<RequestResult<List<Question.Sentence>>> {
        return flow {
            emit(requestResult)
        }
    }
}
