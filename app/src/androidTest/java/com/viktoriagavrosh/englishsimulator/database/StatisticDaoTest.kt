package com.viktoriagavrosh.englishsimulator.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.viktoriagavrosh.englishsimulator.data.database.AppRoomDatabase
import com.viktoriagavrosh.englishsimulator.data.database.StatisticDao
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.model.dbmodel.StatisticDb
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class StatisticDaoTest {
    private lateinit var statisticDao: StatisticDao
    private lateinit var appDatabase: AppRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        statisticDao = appDatabase.statisticDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_insert_insertItem() = runBlocking {
        addItemToDb()
        val expected = FakeSource.fakeStatisticDb[0]
        val actual = statisticDao.getStatisticByDate(expected.date).first().first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_deleteAllStatisticsByMonth_deleteItems() = runBlocking {
        addListItemsToDb()
        val expected = FakeSource.fakeStatisticDb[0]
        statisticDao.deleteAllStatisticsByMonth(expected.date.substring(3, 5))
        val isExist = try {
            statisticDao.getStatisticByDate(expected.date).first().first()
            true
        } catch (e: NoSuchElementException) {
            false
        }
        assert(!isExist)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_update_updateItem() = runBlocking {
        addListItemsToDb()
        val newTranslateScore = 123
        val expected = FakeSource.fakeStatisticDb[1].copy(translateScore = newTranslateScore)
        statisticDao.update(expected)
        val actual = statisticDao.getStatisticByDate(expected.date).first().first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_getStatisticByDate_returnStatistic() = runBlocking {
        addListItemsToDb()
        val expected = FakeSource.fakeStatisticDb[3]
        val actual = statisticDao.getStatisticByDate(expected.date).first().first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_getStatisticByDate_returnEmptyList() = runBlocking {
        addListItemsToDb()
        val expected = emptyList<StatisticDb>()
        val actual = statisticDao.getStatisticByDate("111111").first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_getAllStatisticsByMonth_returnListStatistic() = runBlocking {
        val date = FakeSource.fakeStatisticDb[2].date
        val month = date.substring(3, 5)
        addListItemsToDb()
        val expectedList = FakeSource.fakeStatisticDb.filter { it.date == date }
        val actualList = statisticDao.getAllStatisticsByMonth(month).first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_getAllStatisticsByMonthFromEmptyDb_returnEmptyList() = runBlocking {
        val expectedList = emptyList<StatisticDb>()
        val actualList = statisticDao.getAllStatisticsByMonth("99").first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_getAllStatisticsByMonthByNoExistedMonth_returnsEmptyList() = runBlocking {
        addListItemsToDb()
        val expectedList = emptyList<StatisticDb>()
        val actualList = statisticDao.getAllStatisticsByMonth("39").first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_getAllMonths_returnListStrings() = runBlocking {
        addListItemsToDb()
        val expectedList = FakeSource.fakeStatisticDb.map { it.date.substring(3, 5) }.distinct()
        val actualList = statisticDao.getAllMonths().first()
        assertEquals(expectedList, actualList)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_getAllMonthsFromEmptyDb_returnEmptyList() = runBlocking {
        val expectedList = emptyList<String>()
        val actualList = statisticDao.getAllMonths().first()
        assertEquals(expectedList, actualList)
    }

    private suspend fun addItemToDb() {
        statisticDao.insert(FakeSource.fakeStatisticDb[0])
    }

    private suspend fun addListItemsToDb() {
        for (i in FakeSource.fakeStatisticDb) {
            statisticDao.insert(i)
        }
    }

}
