package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTranslateRepository(
    private val requestResult: RequestResult<List<Sentence>>
) : TranslateRepository {

    override fun getAllItems(): Flow<RequestResult<List<Sentence>>> {
        return flow {
            emit(requestResult)
        }
    }

}
