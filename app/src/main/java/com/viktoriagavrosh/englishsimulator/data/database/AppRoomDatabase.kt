package com.viktoriagavrosh.englishsimulator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.englishsimulator.model.DialogDb
import com.viktoriagavrosh.englishsimulator.model.IssueDb
import com.viktoriagavrosh.englishsimulator.model.SentenceDb

interface AppDatabase {
    fun sentenceDao(): SentenceDao
    fun issueDao(): IssueDao
}

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [SentenceDb::class, IssueDb::class, DialogDb::class],
    version = 2,
    exportSchema = false,
)
internal abstract class AppRoomDatabase : RoomDatabase(), AppDatabase {
    abstract override fun sentenceDao(): SentenceDao
    abstract override fun issueDao(): IssueDao
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
        .fallbackToDestructiveMigration()
        .build()

    return appRoomDatabase
}
