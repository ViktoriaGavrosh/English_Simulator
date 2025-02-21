package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.DialogRepository
import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDialogRepository(
    private val requestResult: RequestResult<List<Question.Dialog>>
) : DialogRepository, GameRepository {

    override fun getAllDialogs(): Flow<RequestResult<List<Question.Dialog>>> {
        return flow {
            emit(requestResult)
        }
    }
}
