package com.viktoriagavrosh.englishsimulator.test.repository

import com.viktoriagavrosh.englishsimulator.data.LocalStatisticRepository
import com.viktoriagavrosh.englishsimulator.fake.FakeDb
import com.viktoriagavrosh.englishsimulator.fake.FakePreferencesManager
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.getMonthByNumber
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toStatistic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class StatisticRepositoryTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val repository = LocalStatisticRepository(FakeDb(), FakePreferencesManager)

    @Test
    fun statisticRepository_getDate_returnDate() {
        runTest {
            val expectedDate = "09-09-9090"
            repository.updateDate(expectedDate)
            val actualDate = repository.getDate().first().data
            assertEquals(expectedDate, actualDate)
        }
    }

    @Test
    fun statisticRepository_getDate_returnRequestResultSuccess() {
        runTest {
            val isSuccess = repository.getDate().first() is RequestResult.Success
            assert(isSuccess)
        }
    }

    @Test
    fun statisticRepository_updateDate_returnDate() {
        runTest {
            val oldDate = repository.getDate().first().data
            repository.updateDate("12-03-2024")
            val actualDate = repository.getDate().first().data
            assertNotEquals(oldDate, actualDate)
        }
    }

    @Test
    fun statisticRepository_getStatisticByDate_returnStatistic() {
        runTest {
            val expectedStatistic = FakeSource.fakeStatisticsDb[1].toStatistic()
            val actualStatistic = repository.getStatisticByDate(expectedStatistic.date)
                .first().data?.first() ?: Statistic()
            assertEquals(expectedStatistic, actualStatistic)
        }
    }

    @Test
    fun statisticRepository_getStatisticByDate_returnRequestResultSuccess() {
        runTest {
            val date = FakeSource.fakeStatisticsDb[3].date
            val isSuccess = repository.getStatisticByDate(date)
                .first() is RequestResult.Success
            assert(isSuccess)
        }
    }

    @Test
    fun statisticRepository_getStatisticByDate_returnRequestResultError() {
        runTest {
            val date = "333"
            val isError = repository.getStatisticByDate(date)
                .first() is RequestResult.Error
            assert(isError)
        }
    }

    @Test
    fun statisticRepository_getAllStatisticsByMonth_returnListStatistic() {
        runTest {
            val monthNum = FakeSource.fakeStatisticsDb[2].date.substring(3, 5)
            val month = getMonthByNumber(monthNum.toInt())
            val expectedList = FakeSource.fakeStatisticsDb
                .filter { it.date.substring(3, 5) == monthNum }
                .map { it.toStatistic() }
            val actualList = repository.getAllStatisticsByMonth(month)
                .first().data ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun statisticRepository_getAllStatisticsByMonth_returnRequestResultSuccess() {
        runTest {
            val month =
                getMonthByNumber(FakeSource.fakeStatisticsDb[1].date.substring(3, 5).toInt())
            val isSuccess = repository.getAllStatisticsByMonth(month)
                .first() is RequestResult.Success
            assert(isSuccess)
        }
    }

    @Test
    fun statisticRepository_getAllMonths_returnListMonths() {
        runTest {
            val expectedList = FakeSource.fakeStatisticsDb
                .map { it.date.substring(3, 5) }
                .distinct()
                .map { getMonthByNumber(it.toInt()) }
            val actualList = repository.getAllMonths()
                .first().data ?: emptyList()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun statisticRepository_getAllMonths_returnRequestResultSuccess() {
        runTest {
            val isSuccess = repository.getAllMonths()
                .first() is RequestResult.Success
            assert(isSuccess)
        }
    }

    @Test
    fun statisticRepository_deleteAllStatisticsByMonth_statisticDeleted() {
        runTest {
            val month = FakeSource.fakeStatisticsDb[1].date.substring(3, 5)
            repository.deleteAllStatisticsByMonth(month)
            val actualList = repository.getAllMonths().first()
                .data ?: listOf(month)
            repository.insertStatistic(FakeSource.fakeStatisticsDb[1].toStatistic())  // because FakeDb is object
            assert(!actualList.contains(month))
        }
    }

    @Test
    fun statisticRepository_insertStatistic_newStatisticInsert() {
        runTest {
            val newDate = "125890"
            val expected = Statistic(date = newDate)
            repository.insertStatistic(expected)
            val actual = repository.getStatisticByDate(newDate)
                .first().data?.first() ?: Statistic()
            repository.deleteAllStatisticsByMonth(
                newDate.substring(
                    3,
                    5
                )
            )   // because FakeDb is object
            assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun statisticRepository_updateStatistic_newStatisticUpdated() {
        runTest {
            val newTranslateScore = 56
            val expected = FakeSource.fakeStatisticsDb[0].copy(translateScore = newTranslateScore)
                .toStatistic()
            repository.updateStatistic(expected)
            val actual = repository.getStatisticByDate(expected.date)
                .first().data?.first() ?: Statistic()
            repository.updateStatistic(FakeSource.fakeStatisticsDb[0].toStatistic())  // because FakeDb is object
            assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun statisticRepository_updateTranslateScore_newStatisticUpdated() {
        runTest {
            val statistic = FakeSource.fakeStatisticsDb[0]
            val expectedScore = statistic.translateScore + 1
            repository.updateTranslateScore(date = statistic.date)
            val actualScore = repository.getStatisticByDate(statistic.date)
                .first().data?.first()?.translateScore ?: 0
            repository.updateStatistic(statistic.toStatistic())  // because FakeDb is object
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticRepository_updateIssueScore_newStatisticUpdated() {
        runTest {
            val statistic = FakeSource.fakeStatisticsDb[1]
            val expectedScore = statistic.issueScore + 1
            repository.updateIssueScore(date = statistic.date)
            val actualScore = repository.getStatisticByDate(statistic.date)
                .first().data?.first()?.issueScore ?: 0
            repository.updateStatistic(statistic.toStatistic())  // because FakeDb is object
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticRepository_updateDialogScore_newStatisticUpdated() {
        runTest {
            val statistic = FakeSource.fakeStatisticsDb[2]
            val expectedScore = statistic.dialogScore + 1
            repository.updateDialogScore(date = statistic.date)
            val actualScore = repository.getStatisticByDate(statistic.date)
                .first().data?.first()?.dialogScore ?: 0
            repository.updateStatistic(statistic.toStatistic())  // because FakeDb is object
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticRepository_updateWordScore_newStatisticUpdated() {
        runTest {
            val statistic = FakeSource.fakeStatisticsDb[3]
            val expectedScore = statistic.wordScore + 1
            repository.updateWordScore(date = statistic.date)
            val actualScore = repository.getStatisticByDate(statistic.date)
                .first().data?.first()?.wordScore ?: 0
            repository.updateStatistic(statistic.toStatistic())  // because FakeDb is object
            assertEquals(expectedScore, actualScore)
        }
    }
}
