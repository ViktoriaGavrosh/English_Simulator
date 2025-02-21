package com.viktoriagavrosh.englishsimulator.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.englishsimulator.model.dbmodel.IssueDb
import kotlinx.coroutines.flow.Flow

@Dao
interface IssueDao {
    /**
     * Return all rows from issue table
     *
     * @return flow of list [IssueDb]
     */
    @Query("SELECT * FROM issue")
    fun getAllIssues(): Flow<List<IssueDb>>

    /**
     * Return all themes from issue table
     *
     * @return flow of list [IssueDb]
     */
    @Query("SELECT DISTINCT theme FROM issue")
    fun getAllThemes(): Flow<List<String>>

    /**
     * Return all rows from issue table by theme
     *
     * @param theme theme of issues
     * @return flow of list [IssueDb]
     */
    @Query("SELECT * FROM issue WHERE theme = :theme")
    fun getAllIssuesByTheme(theme: String): Flow<List<IssueDb>>

    /**
     * will insert element into the database (issue table)
     *
     * @param issueDb object [IssueDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(issueDb: IssueDb)
}
