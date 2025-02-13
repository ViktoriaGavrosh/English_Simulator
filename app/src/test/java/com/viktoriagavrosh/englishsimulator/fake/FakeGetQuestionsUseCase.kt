package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.data.QuestionManager
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetQuestionsUseCase(
    private val requestResult: RequestResult<List<Question>>
) : QuestionManager {
    override fun getAllItems(): Flow<RequestResult<List<Question>>> {
        return flow { emit(requestResult) }
    }

    override fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<Question>>> {
        val items = requestResult.map { list ->
            list.filter { it.theme == theme }
        }
        return flow { emit(items) }
    }
}
