package com.viktoriagavrosh.englishsimulator.data.database

import androidx.room.Dao
import androidx.room.Query
import com.viktoriagavrosh.englishsimulator.model.SentenceDb
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the AppDatabase
 */
@Dao
interface SentenceDao {

    @Query("SELECT * FROM sentence")
    fun getAllSentences(): Flow<List<SentenceDb>>
}
