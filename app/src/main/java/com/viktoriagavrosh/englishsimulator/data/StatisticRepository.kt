package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.getMonthByNumber
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
     * Update translate_score field of Statistic in data source
     *
     * @param date
     */
    suspend fun updateTranslateScore(date: String)

    /**
     * Update issue_score field of Statistic in data source
     *
     * @param date
     */
    suspend fun updateIssueScore(date: String)

    /**
     * Update dialog_score field of Statistic in data source
     *
     * @param date
     */
    suspend fun updateDialogScore(date: String)

    /**
     * Update word_score field of Statistic in data source
     *
     * @param date
     */
    suspend fun updateWordScore(date: String)

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
    fun getAllStatisticsByMonth(month: Month): Flow<RequestResult<List<Statistic>>>

    /**
     * Return all month from data source
     *
     * @return flow of [RequestResult] list [Month]
     */
    fun getAllMonths(): Flow<RequestResult<List<Month>>>

    /**
     * Delete all items from data source by month
     *
     * @param monthNumber from data
     */
    suspend fun deleteAllStatisticsByMonth(monthNumber: String)

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
     * Update translate_score field of Statistic in database
     *
     * @param date
     */
    override suspend fun updateTranslateScore(date: String) {
        database.statisticDao().updateTranslateScore(date)
    }

    /**
     * Update issue_score field of Statistic in database
     *
     * @param date
     */
    override suspend fun updateIssueScore(date: String) {
        database.statisticDao().updateIssueScore(date)
    }

    /**
     * Update dialog_score field of Statistic in database
     *
     * @param date
     */
    override suspend fun updateDialogScore(date: String) {
        database.statisticDao().updateDialogScore(date)
    }

    /**
     * Update word_score field of Statistic in database
     *
     * @param date
     */
    override suspend fun updateWordScore(date: String) {
        database.statisticDao().updateWordScore(date)
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
    override fun getAllStatisticsByMonth(month: Month): Flow<RequestResult<List<Statistic>>> {
        val monthOrdinal = month.ordinal.toString()
        val monthNum = if (monthOrdinal.length < 2) "0$monthOrdinal" else monthOrdinal
        return getRequestResultFlow(
            getFlow = { database.statisticDao().getAllStatisticsByMonth(monthNum) },
            mapper = { it.toStatistic() }
        )
    }

    /**
     * Return all months from data source
     *
     * @return flow of [RequestResult] of list months
     */
    override fun getAllMonths(): Flow<RequestResult<List<Month>>> {
        return getRequestResultFlow(
            getFlow = database.statisticDao()::getAllMonths,
            mapper = { getMonthByNumber(it.toInt()) }
        )
    }

    /**
     * Delete all [Statistic]'s from database by month
     *
     * @param monthNumber from data
     */
    override suspend fun deleteAllStatisticsByMonth(monthNumber: String) {
        database.statisticDao().deleteAllStatisticsByMonth(monthNumber)
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
