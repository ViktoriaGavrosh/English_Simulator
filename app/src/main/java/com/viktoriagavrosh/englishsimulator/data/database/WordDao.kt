package com.viktoriagavrosh.englishsimulator.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriagavrosh.englishsimulator.model.WordDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with word table from DB
 */
@Dao
interface WordDao {

    /**
     * Return all rows from word table
     *
     * @return flow of list [WordDb]
     */
    @Query("SELECT * FROM word")
    fun getAllWords(): Flow<List<WordDb>>

    /**
     * Return all rows from word table by theme
     *
     * @param theme theme of words
     * @return flow of list [WordDb]
     */
    @Query("SELECT * FROM word WHERE theme = :theme")
    fun getAllWordsByTheme(theme: String): Flow<List<WordDb>>

    /**
     * Return all themes from word table
     *
     * @return flow of list [WordDb]
     */
    @Query("SELECT DISTINCT theme FROM word")
    fun getAllThemes(): Flow<List<String>>

    /**
     * will insert element into the database (word table)
     *
     * @param wordDb object [WordDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordDb: WordDb)
}
