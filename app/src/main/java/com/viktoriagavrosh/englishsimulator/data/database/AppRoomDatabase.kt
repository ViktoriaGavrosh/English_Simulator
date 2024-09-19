package com.viktoriagavrosh.englishsimulator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.englishsimulator.model.SentenceDb

interface AppDatabase {
    fun sentenceDao(): SentenceDao
}

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [SentenceDb::class],
    version = 1,
    exportSchema = false,
)
internal abstract class AppRoomDatabase : RoomDatabase(), AppDatabase {
    abstract override fun sentenceDao(): SentenceDao
}

/**
 *  Function build [AppRoomDatabase] object
 *
 *  @param context local context
 *  @return [AppRoomDatabase] object
 */
internal fun getDatabase(context: Context): AppRoomDatabase {
    val appRoomDatabase = Room.databaseBuilder(
        context = context,
        klass = AppRoomDatabase::class.java,
        name = "english"
    )
        .createFromAsset("database/english.db")
        .build()

    return appRoomDatabase
}
