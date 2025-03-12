package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakePreferencesManager
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeStatisticRepository
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.StatisticViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.getMonthByNumber
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.toListScores
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toStatistic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class StatisticViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeStatistics = FakeSource.fakeStatisticsDb.map { it.toStatistic() }

    @Test
    fun statisticViewModel_initUiState_initMonths() {
        runTest {
            val expectedList =
                listOf(fakeStatistics[fakeStatistics.lastIndex - 1], fakeStatistics.last())
                    .map { getMonthByNumber(it.date.substring(3, 5).toInt()) }
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics)
            )
            val actualList = viewModel.getListMonths()
            assertEquals(expectedList, actualList)
        }
    }

    @Test
    fun statisticViewModel_initUiState_initTranslateScores() {
        runTest {
            val expectedMap = fakeStatistics.toListScores { it.translateScore }
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics)
            )
            val actualMap = viewModel.uiState.first().translateScores
            assertEquals(expectedMap, actualMap)
        }
    }

    @Test
    fun statisticViewModel_initUiState_initIssueScores() {
        runTest {
            val expectedMap = fakeStatistics.toListScores { it.issueScore }
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics)
            )
            val actualMap = viewModel.uiState.first().issueScores
            assertEquals(expectedMap, actualMap)
        }
    }

    @Test
    fun statisticViewModel_initUiState_initDialogScores() {
        runTest {
            val expectedMap = fakeStatistics.toListScores { it.dialogScore }
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics)
            )
            val actualMap = viewModel.uiState.first().dialogScores
            assertEquals(expectedMap, actualMap)
        }
    }

    @Test
    fun statisticViewModel_initUiState_initWordScores() {
        runTest {
            val expectedMap = fakeStatistics.toListScores { it.wordScore }
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics)
            )
            val actualMap = viewModel.uiState.first().wordScores
            assertEquals(expectedMap, actualMap)
        }
    }

    @Test
    fun statisticViewModel_initUiState_initIsErrorFalse() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics)
            )
            val actual = viewModel.uiState.first().isError
            assert(!actual)
        }
    }

    @Test
    fun statisticViewModel_initUiState_initIsErrorTrue() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Error()
            )
            val actual = viewModel.uiState.first().isError
            assert(actual)
        }
    }

    @Test
    fun statisticViewModel_updateUiState_uiStateUpdated() {
        runTest {
            val newMonthNum = fakeStatistics[fakeStatistics.lastIndex - 1].date
                .substring(3, 5).toInt()
            val expectedTranslateMap = fakeStatistics
                .filter { it.date.substring(3, 5).toInt() == newMonthNum }
                .toListScores { it.translateScore }
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics)
            )
            val index = viewModel.getListMonths().indexOf(getMonthByNumber(newMonthNum))
            viewModel.updateUiState(index)
            val actualTranslateMap = viewModel.uiState.first().translateScores
            assertEquals(expectedTranslateMap, actualTranslateMap)
        }
    }

    @Test
    fun listStatistic_toListScores_returnList() {
        runTest {
            val expected = MutableList(31) { 0 }
            for (i in fakeStatistics) {
                val day = i.date.take(2).toInt()
                expected[day - 1] = i.translateScore
            }
            val actual = fakeStatistics.toListScores { it.translateScore }
            assertEquals(expected, actual)
        }
    }

    @Test
    fun listStatistic_toListScoresWithEmptyList_returnList() {
        runTest {
            val expected = MutableList(31) { 0 }
            val actual = emptyList<Statistic>().toListScores { it.translateScore }
            assertEquals(expected, actual)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Statistic>> = RequestResult.Success(fakeStatistics),
    ): StatisticViewModel {
        return StatisticViewModel(
            repository = FakeStatisticRepository(requestResult),
            manager = FakePreferencesManager()
        )
    }

}
