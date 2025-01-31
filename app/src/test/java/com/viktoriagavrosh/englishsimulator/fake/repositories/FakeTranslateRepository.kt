package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTranslateRepository(
    private val requestResult: RequestResult<List<UiItem>>
) : TranslateRepository, GameRepository {

    override fun getAllItems(): Flow<RequestResult<List<UiItem>>> {
        return flow {
            emit(requestResult)
        }
    }

    override fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<UiItem>>> {
        return getAllItems()
    }

}
