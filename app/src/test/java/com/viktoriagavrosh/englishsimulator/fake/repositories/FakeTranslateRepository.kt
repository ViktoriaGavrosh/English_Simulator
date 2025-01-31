package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTranslateRepository(
    private val requestResult: RequestResult<List<UiItem>>
) : TranslateRepository {

    override fun getAllItems(): Flow<RequestResult<List<UiItem>>> {
        return flow {
            emit(requestResult)
        }
    }

}
