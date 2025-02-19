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
        val lastMonth = FakeSource.fakeStatisticDb[0].date.substring(3)
        val thisMonth = FakeSource.fakeStatisticDb[1].date.substring(3)
        statisticDao.deleteAllStatisticsByMonth(thisMonth, lastMonth)
        val deletedMonth = FakeSource.fakeStatisticDb[2].date.substring(3, 5)
        val actualList = statisticDao.getAllStatisticsByMonth(deletedMonth).first()
        assertEquals(emptyList<StatisticDb>(), actualList)
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

    @Test
    @Throws(Exception::class)
    fun statisticDao_updateTranslateScore_updateItem() = runBlocking {
        addListItemsToDb()
        val statistic = FakeSource.fakeStatisticDb[2]
        val expected = statistic.copy(translateScore = statistic.translateScore + 1)
        statisticDao.updateTranslateScore(date = statistic.date)
        val actual = statisticDao.getStatisticByDate(statistic.date).first().first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_updateIssueScore_updateItem() = runBlocking {
        addListItemsToDb()
        val statistic = FakeSource.fakeStatisticDb[1]
        val expected = statistic.copy(issueScore = statistic.issueScore + 1)
        statisticDao.updateIssueScore(date = statistic.date)
        val actual = statisticDao.getStatisticByDate(statistic.date).first().first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_updateDialogScore_updateItem() = runBlocking {
        addListItemsToDb()
        val statistic = FakeSource.fakeStatisticDb[0]
        val expected = statistic.copy(dialogScore = statistic.dialogScore + 1)
        statisticDao.updateDialogScore(date = statistic.date)
        val actual = statisticDao.getStatisticByDate(statistic.date).first().first()
        assertEquals(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun statisticDao_updateWordScore_updateItem() = runBlocking {
        addListItemsToDb()
        val statistic = FakeSource.fakeStatisticDb[4]
        val expected = statistic.copy(wordScore = statistic.wordScore + 1)
        statisticDao.updateWordScore(date = statistic.date)
        val actual = statisticDao.getStatisticByDate(statistic.date).first().first()
        assertEquals(expected, actual)
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
