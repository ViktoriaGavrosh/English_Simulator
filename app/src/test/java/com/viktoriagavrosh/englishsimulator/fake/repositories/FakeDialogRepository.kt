package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.DialogRepository
import com.viktoriagavrosh.englishsimulator.model.Dialog
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDialogRepository(
    private val requestResult: RequestResult<List<Dialog>>
) : DialogRepository {

    override fun getAllItems(): Flow<RequestResult<List<Dialog>>> {
        return flow {
            emit(requestResult)
        }
    }

}
