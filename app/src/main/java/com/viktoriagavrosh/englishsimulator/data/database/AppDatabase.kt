package com.viktoriagavrosh.englishsimulator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.englishsimulator.model.SentenceDb

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [SentenceDb::class],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun sentenceDao(): SentenceDao
}

/**
 *  Function build [AppDatabase] object
 */
internal fun getDatabase(context: Context): AppDatabase {
    val appRoomDatabase = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "english"
    )
        .createFromAsset("database/english.db")
        //.fallbackToDestructiveMigration()  TODO 111
        .build()

    return appRoomDatabase
}
