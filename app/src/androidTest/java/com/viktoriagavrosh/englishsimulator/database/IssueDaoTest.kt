package com.viktoriagavrosh.englishsimulator.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.viktoriagavrosh.englishsimulator.data.database.AppRoomDatabase
import com.viktoriagavrosh.englishsimulator.data.database.IssueDao
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class IssueDaoTest {
    private lateinit var issueDao: IssueDao
    private lateinit var appDatabase: AppRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        issueDao = appDatabase.issueDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun issueDao_insert_insertItem() = runBlocking {
        addItemToDb()
        val expectedIssue = FakeSource.fakeIssuesDb[0]
        val actualIssue = issueDao.getAllIssues().first().first()
        assertEquals(expectedIssue, actualIssue)
    }

    @Test
    @Throws(Exception::class)
    fun issueDao_getAllIssues_returnListIssues() = runBlocking {
        addListItemsToDb()
        val expectedList = FakeSource.fakeIssuesDb
        val actualList = issueDao.getAllIssues().first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun issueDao_getAllIssuesFromEmptyDb_returnsEmptyList() = runBlocking {
        val isEmptyList = issueDao.getAllIssues().first().isEmpty()
        assert(isEmptyList)
    }

    @Test
    @Throws(Exception::class)
    fun issueDao_getAllIssuesByTheme_returnListIssues() = runBlocking {
        val theme = FakeSource.fakeIssuesDb[0].theme
        addListItemsToDb()
        val expectedList = FakeSource.fakeIssuesDb.filter { it.theme == theme }
        val actualList = issueDao.getAllIssuesByTheme(theme).first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun issueDao_getAllIssuesByThemeFromEmptyDb_returnsEmptyList() = runBlocking {
        val theme = FakeSource.fakeIssuesDb[0].theme
        val isEmptyList = issueDao.getAllIssuesByTheme(theme).first().isEmpty()
        assert(isEmptyList)
    }

    @Test
    @Throws(Exception::class)
    fun issueDao_getAllIssuesByNoExistedTheme_returnsEmptyList() = runBlocking {
        addListItemsToDb()
        val theme = ""
        val isEmptyList = issueDao.getAllIssuesByTheme(theme).first().isEmpty()
        assert(isEmptyList)
    }

    @Test
    @Throws(Exception::class)
    fun issueDao_getAllThemes_returnListStrings() = runBlocking {
        addListItemsToDb()
        val expectedList = FakeSource.fakeIssuesDb.map { it.theme }.distinct()
        val actualList = issueDao.getAllThemes().first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun issueDao_getAllThemesFromEmptyDb_returnsEmptyList() = runBlocking {
        val isEmptyList = issueDao.getAllThemes().first().isEmpty()
        assert(isEmptyList)
    }

    private suspend fun addItemToDb() {
        issueDao.insert(FakeSource.fakeIssuesDb[0])
    }

    private suspend fun addListItemsToDb() {
        for (i in FakeSource.fakeIssuesDb) {
            issueDao.insert(i)
        }
    }

}
