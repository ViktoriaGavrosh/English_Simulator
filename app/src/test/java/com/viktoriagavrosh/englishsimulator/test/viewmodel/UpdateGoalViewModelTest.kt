package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakePreferencesManager
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.ui.features.goal.UpdateGoalViewModel
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class UpdateGoalViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun updateGoalViewModel_initUiState_initGoalsUiState() {
        runTest {
            val expected = FakeSource.fakeGoal
            val viewModel = initViewModel()
            val actual = viewModel.goalsUiState.first()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun updateGoalViewModel_updateTranslateGoal_goalsUiStateUpdated() {
        runTest {
            val newGoal = 27
            val expected = FakeSource.fakeGoal.copy(translateGoal = newGoal)
            val viewModel = initViewModel()
            viewModel.updateTranslateGoal(newGoal.toString())
            val actual = viewModel.goalsUiState.first()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun updateGoalViewModel_updateIssueGoal_goalsUiStateUpdated() {
        runTest {
            val newGoal = 46
            val expected = FakeSource.fakeGoal.copy(issueGoal = newGoal)
            val viewModel = initViewModel()
            viewModel.updateIssueGoal(newGoal.toString())
            val actual = viewModel.goalsUiState.first()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun updateGoalViewModel_updateDialogGoal_goalsUiStateUpdated() {
        runTest {
            val newGoal = 62
            val expected = FakeSource.fakeGoal.copy(dialogGoal = newGoal)
            val viewModel = initViewModel()
            viewModel.updateDialogGoal(newGoal.toString())
            val actual = viewModel.goalsUiState.first()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun updateGoalViewModel_updateWordGoal_goalsUiStateUpdated() {
        runTest {
            val newGoal = 85
            val expected = FakeSource.fakeGoal.copy(wordGoal = newGoal)
            val viewModel = initViewModel()
            viewModel.updateWordGoal(newGoal.toString())
            val actual = viewModel.goalsUiState.first()
            assertEquals(expected, actual)
        }
    }

    private fun initViewModel(): UpdateGoalViewModel {
        return UpdateGoalViewModel(
            manager = FakePreferencesManager()
        )
    }
}
