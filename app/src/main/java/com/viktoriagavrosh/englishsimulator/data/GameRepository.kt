package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.Flow

/**
 * provide data for ui (GameScreen) from data source
 */
interface GameRepository {
    /**
     * Retrieve all items from given data source
     *
     * @return flow of [RequestResult] with list [UiItem]
     */
    fun getAllItems(): Flow<RequestResult<List<UiItem>>>

    /**
     * Retrieve all items from given data source by theme
     *
     * @param theme theme of items
     * @return flow of [RequestResult] with list [UiItem]
     */
    fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<UiItem>>>
}
