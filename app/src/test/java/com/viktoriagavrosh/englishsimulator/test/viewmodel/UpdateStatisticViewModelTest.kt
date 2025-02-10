package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeUpdateStatisticRepository
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.navigation.UpdateStatisticViewModel
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toStatistic
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class UpdateStatisticViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeStatistics = FakeSource.fakeStatisticsDb.map { it.toStatistic() }

    @Test
    fun statisticViewModel_prepareDatabase_initNewDate() {
        runTest {
            val newDate = "15-07-2015"
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics),
                date = newDate
            )
            val isDateExist = viewModel.getStatisticByDate(newDate) != Statistic()
            assert(isDateExist)
        }
    }

    @Test
    fun statisticViewModel_prepareDatabase_statisticsDeleted() {
        runTest {
            val newDate = "01-04-2015"
            val dateForDelete = "00-02-0000"
            val newFakeStatistics = fakeStatistics.toMutableList()
            newFakeStatistics.add(Statistic(date = dateForDelete))
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(newFakeStatistics),
                date = newDate
            )
            val isStatisticNotExist = viewModel.getStatisticByDate(dateForDelete) == Statistic()
            assert(isStatisticNotExist)
        }
    }

    @Test
    fun statisticViewModel_prepareDatabase_statisticsDeletedTwelfthMonth() {
        runTest {
            val newDate = "01-02-2015"
            val dateForDelete = "00-12-0000"
            val newFakeStatistics = fakeStatistics.toMutableList()
            newFakeStatistics.add(Statistic(date = dateForDelete))
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(newFakeStatistics),
                date = newDate
            )
            val isStatisticNotExist = viewModel.getStatisticByDate(dateForDelete) == Statistic()
            assert(isStatisticNotExist)
        }
    }

    @Test
    fun statisticViewModel_updateTranslateScore_statisticUpdated() {
        runTest {
            val newScore = 23
            val date = "00-00-0001"
            val startScore = 14
            val newFakeStatistics = fakeStatistics.toMutableList()
            newFakeStatistics.add(Statistic(date = date, translateScore = startScore))
            val expectedScore = startScore + newScore
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(newFakeStatistics),
                date = date
            )
            viewModel.updateTranslateScore(newScore)
            val actualScore = viewModel.getStatisticByDate(date).translateScore
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticViewModel_updateIssueScore_statisticUpdated() {
        runTest {
            val newScore = 23
            val date = "00-00-0001"
            val startScore = 14
            val newFakeStatistics = fakeStatistics.toMutableList()
            newFakeStatistics.add(Statistic(date = date, issueScore = startScore))
            val expectedScore = startScore + newScore
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(newFakeStatistics),
                date = date
            )
            viewModel.updateIssueScore(newScore)
            val actualScore = viewModel.getStatisticByDate(date).issueScore
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticViewModel_updateDialogScore_statisticUpdated() {
        runTest {
            val newScore = 23
            val date = "00-00-0001"
            val startScore = 14
            val newFakeStatistics = fakeStatistics.toMutableList()
            newFakeStatistics.add(Statistic(date = date, dialogScore = startScore))
            val expectedScore = startScore + newScore
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(newFakeStatistics),
                date = date
            )
            viewModel.updateDialogScore(newScore)
            val actualScore = viewModel.getStatisticByDate(date).dialogScore
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticViewModel_updateWordScore_statisticUpdated() {
        runTest {
            val newScore = 23
            val date = "00-00-0001"
            val startScore = 14
            val newFakeStatistics = fakeStatistics.toMutableList()
            newFakeStatistics.add(Statistic(date = date, wordScore = startScore))
            val expectedScore = startScore + newScore
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(newFakeStatistics),
                date = date
            )
            viewModel.updateWordScore(newScore)
            val actualScore = viewModel.getStatisticByDate(date).wordScore
            assertEquals(expectedScore, actualScore)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Statistic>> = RequestResult.Success(fakeStatistics),
        date: String = "00-00-0000"
    ): UpdateStatisticViewModel {
        return UpdateStatisticViewModel(
            repository = FakeUpdateStatisticRepository(requestResult),
            date = date
        )
    }
}
