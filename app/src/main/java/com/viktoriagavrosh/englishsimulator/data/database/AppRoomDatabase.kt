package com.viktoriagavrosh.englishsimulator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.englishsimulator.model.dbmodel.DialogDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.IssueDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.SentenceDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.StatisticDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.WordDb

/**
 * Database interface
 */
interface AppDatabase {
    fun sentenceDao(): SentenceDao
    fun issueDao(): IssueDao
    fun dialogDao(): DialogDao
    fun wordDao(): WordDao
    fun statisticDao(): StatisticDao
}

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [SentenceDb::class, IssueDb::class, DialogDb::class, WordDb::class, StatisticDb::class],
    version = 4,
    exportSchema = false,
)
internal abstract class AppRoomDatabase : RoomDatabase(), AppDatabase {
    abstract override fun sentenceDao(): SentenceDao
    abstract override fun issueDao(): IssueDao
    abstract override fun dialogDao(): DialogDao
    abstract override fun wordDao(): WordDao
    abstract override fun statisticDao(): StatisticDao
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
        //.fallbackToDestructiveMigration()           // TODO only for develop brunch
        .build()

    return appRoomDatabase
}
