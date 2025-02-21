package com.viktoriagavrosh.englishsimulator.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.viktoriagavrosh.englishsimulator.data.database.AppRoomDatabase
import com.viktoriagavrosh.englishsimulator.data.database.WordDao
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class WordDaoTest {
    private lateinit var wordDao: WordDao
    private lateinit var appDatabase: AppRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        wordDao = appDatabase.wordDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_insert_insertItem() = runBlocking {
        addItemToDb()
        val expected = FakeSource.fakeWordsDb[0]
        val actual = wordDao.getAllWords().first().first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_delete_deleteItem() = runBlocking {
        addListItemsToDb()
        val expected = FakeSource.fakeWordsDb[0]
        wordDao.delete(expected)
        val isExist = try {
            wordDao.getWordById(expected.id).first().id != 0
        } catch (e: Exception) {
            false
        }
        assert(!isExist)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_update_updateItem() = runBlocking {
        addListItemsToDb()
        val newText = "newText"
        val expected = FakeSource.fakeWordsDb[0].copy(englishWord = newText)
        wordDao.update(expected)
        val actual = wordDao.getWordById(expected.id).first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_getAllWords_returnListWords() = runBlocking {
        addListItemsToDb()
        val expectedList = FakeSource.fakeWordsDb
        val actualList = wordDao.getAllWords().first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_getAllWordsFromEmptyDb_returnsEmptyList() = runBlocking {
        val isEmptyList = wordDao.getAllWords().first().isEmpty()
        assert(isEmptyList)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_getWordById_returnWord() = runBlocking {
        addListItemsToDb()
        val expected = FakeSource.fakeWordsDb[0]
        val actual = wordDao.getWordById(expected.id).first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_getAllWordsByTheme_returnListWords() = runBlocking {
        val theme = FakeSource.fakeWordsDb[0].theme
        addListItemsToDb()
        val expectedList = FakeSource.fakeWordsDb.filter { it.theme == theme }
        val actualList = wordDao.getAllWordsByTheme(theme).first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_getAllWordsByThemeFromEmptyDb_returnsEmptyList() = runBlocking {
        val theme = FakeSource.fakeWordsDb[0].theme
        val isEmptyList = wordDao.getAllWordsByTheme(theme).first().isEmpty()
        assert(isEmptyList)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_getAllWordsByNoExistedTheme_returnsEmptyList() = runBlocking {
        addListItemsToDb()
        val theme = ""
        val isEmptyList = wordDao.getAllWordsByTheme(theme).first().isEmpty()
        assert(isEmptyList)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_getAllThemes_returnListStrings() = runBlocking {
        addListItemsToDb()
        val expectedList = FakeSource.fakeWordsDb.map { it.theme }.distinct()
        val actualList = wordDao.getAllThemes().first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun wordDao_getAllThemesFromEmptyDb_returnsEmptyList() = runBlocking {
        val isEmptyList = wordDao.getAllThemes().first().isEmpty()
        assert(isEmptyList)
    }

    private suspend fun addItemToDb() {
        wordDao.insert(FakeSource.fakeWordsDb[0])
    }

    private suspend fun addListItemsToDb() {
        for (i in FakeSource.fakeWordsDb) {
            wordDao.insert(i)
        }
    }

}
