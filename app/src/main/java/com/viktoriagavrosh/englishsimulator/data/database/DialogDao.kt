package com.viktoriagavrosh.englishsimulator.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.englishsimulator.model.DialogDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with dialog table from DB
 */
@Dao
interface DialogDao {

    /**
     * Return all rows from dialog table
     *
     * @return flow of list [DialogDb]
     */
    @Query("SELECT * FROM dialog")
    fun getAllDialogs(): Flow<List<DialogDb>>

    /**
     * will insert element into the database (dialog table)
     *
     * @param dialogDb object [DialogDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dialogDb: DialogDb)
}
