package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeStatisticRepository
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
    fun statisticViewModel_updateTranslateScore_statisticUpdated() {
        runTest {
            val statistic = fakeStatistics[3]
            val score = statistic.translateScore
            val expectedScore = score + 1
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics),
                date = statistic.date
            )
            viewModel.updateTranslateScore()
            val actualScore = viewModel.getStatisticByDate(statistic.date).translateScore
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticViewModel_updateIssueScore_statisticUpdated() {
        runTest {
            val statistic = fakeStatistics[0]
            val score = statistic.issueScore
            val expectedScore = score + 1
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics),
                date = statistic.date
            )
            viewModel.updateIssueScore()
            val actualScore = viewModel.getStatisticByDate(statistic.date).issueScore
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticViewModel_updateDialogScore_statisticUpdated() {
        runTest {
            val statistic = fakeStatistics[2]
            val score = statistic.dialogScore
            val expectedScore = score + 1
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics),
                date = statistic.date
            )
            viewModel.updateDialogScore()
            val actualScore = viewModel.getStatisticByDate(statistic.date).dialogScore
            assertEquals(expectedScore, actualScore)
        }
    }

    @Test
    fun statisticViewModel_updateWordScore_statisticUpdated() {
        runTest {
            val statistic = fakeStatistics[1]
            val score = statistic.wordScore
            val expectedScore = score + 1
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics),
                date = statistic.date
            )
            viewModel.updateWordScore()
            val actualScore = viewModel.getStatisticByDate(statistic.date).wordScore
            assertEquals(expectedScore, actualScore)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Statistic>> = RequestResult.Success(fakeStatistics),
        date: String = "00-00-0000"
    ): UpdateStatisticViewModel {
        return UpdateStatisticViewModel(
            repository = FakeStatisticRepository(requestResult),
            date = date
        )
    }

}
