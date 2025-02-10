package com.viktoriagavrosh.englishsimulator.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.viktoriagavrosh.englishsimulator.model.dbmodel.StatisticDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with statistic table from DB
 */
@Dao
interface StatisticDao {

    /**
     * Return item from statistic table source by date
     *
     * @param date
     * @return flow of list [StatisticDb]
     */
    @Query("SELECT * FROM statistic WHERE date = :date")
    fun getStatisticByDate(date: String): Flow<List<StatisticDb>>

    /**
     * Return all rows from statistic table by month
     *
     * @param month from date
     * @return flow of list [StatisticDb]
     */
    @Query("SELECT * FROM statistic WHERE date LIKE (SELECT '%-'||:month||'-%')")
    fun getAllStatisticsByMonth(month: String): Flow<List<StatisticDb>>

    /**
     * Return all month from statistic table
     *
     * @return flow of list months
     */
    @Query("SELECT DISTINCT SUBSTR(date, 4, 2) FROM statistic")
    fun getAllMonths(): Flow<List<String>>

    /**
     * Update translate_score field of item in statistic table
     *
     * @param date
     * @param score new value
     */
    @Query("UPDATE statistic SET translate_score = :score WHERE date = :date")
    suspend fun updateTranslateScore(date: String, score: Int)

    /**
     * Update issue_score field of item in statistic table
     *
     * @param date
     * @param score new value
     */
    @Query("UPDATE statistic SET issue_score = :score WHERE date = :date")
    suspend fun updateIssueScore(date: String, score: Int)

    /**
     * Update dialog_score field of item in statistic table
     *
     * @param date
     * @param score new value
     */
    @Query("UPDATE statistic SET dialog_score = :score WHERE date = :date")
    suspend fun updateDialogScore(date: String, score: Int)

    /**
     * Update word_score field of item in statistic table
     *
     * @param date
     * @param score new value
     */
    @Query("UPDATE statistic SET word_score = :score WHERE date = :date")
    suspend fun updateWordScore(date: String, score: Int)

    /**
     * Delete all items from statistic table by month
     *
     * @param month from data
     */
    @Query("DELETE FROM statistic WHERE date LIKE (SELECT '%-'||:month||'-%')")
    suspend fun deleteAllStatisticsByMonth(month: String)

    /**
     * will insert element into the database (statistic table)
     *
     * @param statisticDb object [StatisticDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(statisticDb: StatisticDb)

    /**
     * will update element into the database (statistic table)
     *
     * @param statisticDb object [StatisticDb] that will be update
     */
    @Update
    suspend fun update(statisticDb: StatisticDb)
}
