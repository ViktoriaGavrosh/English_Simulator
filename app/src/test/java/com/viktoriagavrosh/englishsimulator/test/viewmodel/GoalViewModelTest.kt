package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakePreferencesManager
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeStatisticRepository
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.goal.GoalViewModel
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toStatistic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GoalViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeStatistics = FakeSource.fakeStatisticsDb.map { it.toStatistic() }

    @Test
    fun goalViewModel_initUiState_initDayStatisticState() {
        runTest {
            val expectedStatistic = fakeStatistics[3]
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeStatistics),
                date = expectedStatistic.date,
            )
            val actualStatistic = viewModel.dayStatisticState.first().data?.first() ?: Statistic()
            assertEquals(expectedStatistic, actualStatistic)
        }
    }

    @Test
    fun goalViewModel_initUiState_initGoalState() {
        runTest {
            val expectedGoal = FakeSource.fakeGoal
            val viewModel = initViewModel()
            val actualGoal = viewModel.goalState.first()
            assertEquals(expectedGoal, actualGoal)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Statistic>> = RequestResult.Success(fakeStatistics),
        date: String = FakeSource.fakeStatisticsDb[0].date,
    ): GoalViewModel {
        return GoalViewModel(
            repository = FakeStatisticRepository(requestResult),
            date = date,
            manager = FakePreferencesManager()
        )
    }
}
