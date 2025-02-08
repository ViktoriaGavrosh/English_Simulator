package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.getRequestResultFlow
import com.viktoriagavrosh.englishsimulator.utils.toStatistic
import com.viktoriagavrosh.englishsimulator.utils.toStatisticDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * provide data for statistic from data source
 */
interface StatisticRepository {

    /**
     * Retrieve item from given data source
     *
     * @return flow of [RequestResult] of [String]
     */
    fun getDate(): Flow<RequestResult<String>>

    /**
     * Update value of item from given data source
     *
     * @param date new value
     */
    suspend fun updateDate(date: String)

    /**
     * Return item from data source by date
     *
     * @param date
     * @return flow of [RequestResult] list [Statistic]
     */
    fun getStatisticByDate(date: String): Flow<RequestResult<List<Statistic>>>

    /**
     * Return all items from data source by month
     *
     * @param month from date
     * @return flow of [RequestResult] list [Statistic]
     */
    fun getAllStatisticsByMonth(month: String): Flow<RequestResult<List<Statistic>>>

    /**
     * Return all month from data source
     *
     * @return flow of [RequestResult] list [String]
     */
    fun getAllMonths(): Flow<RequestResult<List<String>>>

    /**
     * Delete all items from data source by month
     *
     * @param month from data
     */
    suspend fun deleteAllStatisticsByMonth(month: String)

    /**
     * will insert element into the data source
     *
     * @param statistic object [Statistic] that will be insert
     */
    suspend fun insertStatistic(statistic: Statistic)

    /**
     * will update element into the data source
     *
     * @param statistic object [Statistic] that will be update
     */
    suspend fun updateStatistic(statistic: Statistic)
}

/**
 * provide data for statistic from local database
 *
 * @param database instance of local database
 * @param preferencesManager instance of DataStore
 */
class LocalStatisticRepository(
    private val database: AppDatabase,
    private val preferencesManager: PreferencesManager
) : StatisticRepository {

    /**
     * Retrieve date from DataStore
     *
     * @return flow of [RequestResult] of [String]
     */
    override fun getDate(): Flow<RequestResult<String>> {
        return try {
            preferencesManager.getDate()
                .map { RequestResult.Success(it) }
        } catch (e: Exception) {
            flow { emit(RequestResult.Error()) }
        }
    }

    /**
     * Update value of date from DataStore
     *
     * @param date new value
     */
    override suspend fun updateDate(date: String) {
        preferencesManager.updateDate(date)
    }

    /**
     * Return [Statistic]'s from database by date
     *
     * @param date
     * @return flow of [RequestResult] of list [Statistic]
     */
    override fun getStatisticByDate(date: String): Flow<RequestResult<List<Statistic>>> {
        return getRequestResultFlow(
            getFlow = { database.statisticDao().getStatisticByDate(date) },
            mapper = { it.toStatistic() }
        )
    }

    /**
     * Return all [Statistic]'s from database by month
     *
     * @param month from date
     * @return flow of [RequestResult] of list [Statistic]
     */
    override fun getAllStatisticsByMonth(month: String): Flow<RequestResult<List<Statistic>>> {
        return getRequestResultFlow(
            getFlow = { database.statisticDao().getAllStatisticsByMonth(month) },
            mapper = { it.toStatistic() }
        )
    }

    /**
     * Return all months from data source
     *
     * @return flow of [RequestResult] of list months
     */
    override fun getAllMonths(): Flow<RequestResult<List<String>>> {
        return getRequestResultFlow(
            getFlow = database.statisticDao()::getAllMonths,
            mapper = { it }
        )
    }

    /**
     * Delete all [Statistic]'s from database by month
     *
     * @param month from data
     */
    override suspend fun deleteAllStatisticsByMonth(month: String) {
        database.statisticDao().deleteAllStatisticsByMonth(month)
    }

    /**
     * will insert [Statistic] into database
     *
     * @param statistic object [Statistic] that will be insert
     */
    override suspend fun insertStatistic(statistic: Statistic) {
        database.statisticDao().insert(statistic.toStatisticDb())
    }

    /**
     * will update [Statistic] into the database
     *
     * @param statistic object [Statistic] that will be update
     */
    override suspend fun updateStatistic(statistic: Statistic) {
        database.statisticDao().update(statistic.toStatisticDb())
    }

}
