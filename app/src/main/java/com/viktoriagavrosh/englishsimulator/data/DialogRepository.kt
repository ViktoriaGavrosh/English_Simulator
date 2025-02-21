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
interface DialogRepository {

    /**
     * Retrieve all items from given data source
     *
     * @return flow of [RequestResult] with list [Question.Dialog]
     */
    fun getAllDialogs(): Flow<RequestResult<List<Question.Dialog>>>
}

/**
 * provide data for ui from local database
 *
 * @param database instance of local database
 */
internal class LocalDialogRepository(
    private val database: AppDatabase
) : DialogRepository, GameRepository {

    /**
     * Retrieve all [Question.Dialog] from database
     *
     * @return flow of [RequestResult] with list [Question.Dialog]
     */
    override fun getAllDialogs(): Flow<RequestResult<List<Question.Dialog>>> {
        return getRequestResultFlow(
            getFlow = database.dialogDao()::getAllDialogs,
            mapper = { it.toQuestion() as Question.Dialog }
        )
    }
}

