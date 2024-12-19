package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.Dialog
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toDialog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * provide data for ui from data source
 */
interface DialogRepository {

    /**
     * Retrieve all items from given data source
     *
     * @return flow of [RequestResult] with list [Dialog]
     */
    fun getAllDialogs(): Flow<RequestResult<List<Dialog>>>
}

/**
 * provide data for ui from local database
 *
 * @param database instance of local database
 */
internal class LocalDialogRepository(
    private val database: AppDatabase
) : DialogRepository {

    /**
     * Retrieve all [Dialog] from database
     *
     * @return flow of [RequestResult] with list [Dialog]
     */
    override fun getAllDialogs(): Flow<RequestResult<List<Dialog>>> {
        return try {
            database.dialogDao().getAllDialogs()
                .map { list ->
                    list.map { it.toDialog() }
                }
                .map<List<Dialog>, RequestResult<List<Dialog>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }
}

