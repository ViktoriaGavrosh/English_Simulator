package com.viktoriagavrosh.englishsimulator.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.englishsimulator.model.SentenceDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with sentence table from DB
 */
@Dao
interface SentenceDao {

    /**
     * Return all rows from sentence table
     *
     * @return flow of list [SentenceDb]
     */
    @Query("SELECT * FROM sentence")
    fun getAllSentences(): Flow<List<SentenceDb>>

    /**
     * will insert element into the database (sentence table)
     *
     * @param sentenceDb object [SentenceDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sentenceDb: SentenceDb)
}
