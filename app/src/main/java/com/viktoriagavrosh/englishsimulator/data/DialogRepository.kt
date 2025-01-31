package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.toUiItem
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
     * @return flow of [RequestResult] with list [UiItem]
     */
    fun getAllItems(): Flow<RequestResult<List<UiItem>>>
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
     * Retrieve all [UiItem] from database
     *
     * @return flow of [RequestResult] with list [UiItem]
     */
    override fun getAllItems(): Flow<RequestResult<List<UiItem>>> {
        return try {
            database.dialogDao().getAllDialogs()
                .map { list ->
                    list.map { it.toUiItem() }
                }
                .map<List<UiItem>, RequestResult<List<UiItem>>> { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow {
                emit(RequestResult.Error(e))
            }
        }
    }

    override fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<UiItem>>> {
        return getAllItems()     // because Dialog doesn't have theme field
    }
}

