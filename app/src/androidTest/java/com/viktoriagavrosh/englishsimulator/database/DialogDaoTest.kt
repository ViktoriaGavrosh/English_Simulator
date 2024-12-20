package com.viktoriagavrosh.englishsimulator.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.viktoriagavrosh.englishsimulator.data.database.AppRoomDatabase
import com.viktoriagavrosh.englishsimulator.data.database.DialogDao
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DialogDaoTest {

    private lateinit var dialogDao: DialogDao
    private lateinit var appDatabase: AppRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dialogDao = appDatabase.dialogDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun dialogDao_insert_insertItem() = runBlocking {
        addItemToDb()
        val expectedItem = FakeSource.fakeDialogsDb[0]
        val actualItem = dialogDao.getAllDialogs().first().first()
        assertEquals(expectedItem, actualItem)
    }

    @Test
    @Throws(Exception::class)
    fun dialogDao_getAllDialogs_returnListDialogs() = runBlocking {
        addListItemsToDb()
        val expectedList = FakeSource.fakeDialogsDb
        val actualList = dialogDao.getAllDialogs().first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun dialogDao_getAllDialogsFromEmptyDb_returnsEmptyList() = runBlocking {
        val isEmptyList = dialogDao.getAllDialogs().first().isEmpty()
        assert(isEmptyList)
    }

    private suspend fun addItemToDb() {
        dialogDao.insert(FakeSource.fakeDialogsDb[0])
    }

    private suspend fun addListItemsToDb() {
        for (i in FakeSource.fakeDialogsDb) {
            dialogDao.insert(i)
        }
    }
}
