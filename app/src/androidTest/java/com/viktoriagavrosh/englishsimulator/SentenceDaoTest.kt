package com.viktoriagavrosh.englishsimulator

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.viktoriagavrosh.englishsimulator.data.database.AppRoomDatabase
import com.viktoriagavrosh.englishsimulator.data.database.SentenceDao
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class SentenceDaoTest {

    private lateinit var taleDao: SentenceDao
    private lateinit var appDatabase: AppRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        taleDao = appDatabase.sentenceDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun sentenceDao_Insert_insertSentence() = runBlocking {
        addItemToDb()
        val expectedSentence = FakeSource.fakeSentencesDb[0]
        val actualSentence = taleDao.getAllSentences().first().first()
        assertEquals(expectedSentence, actualSentence)
    }

    @Test
    @Throws(Exception::class)
    fun sentenceDao_GetAllSentencesFromDB_returnListSentences() = runBlocking {
        addListItemsToDb()
        val expectedList = FakeSource.fakeSentencesDb
        val actualList = taleDao.getAllSentences().first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun sentenceDao_GetAllSentencesFromEmptyDb_returnsEmptyList() = runBlocking {
        val isEmptyList = taleDao.getAllSentences().first().isEmpty()
        assert(isEmptyList)
    }

    private suspend fun addItemToDb() {
        taleDao.insert(FakeSource.fakeSentencesDb[0])
    }

    private suspend fun addListItemsToDb() {
        for (i in FakeSource.fakeSentencesDb) {
            taleDao.insert(i)
        }
    }
}
