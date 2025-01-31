package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeIssueRepository
import com.viktoriagavrosh.englishsimulator.model.Issue
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueGameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.toIssue
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toUiItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class IssueGameViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeIssues = FakeSource.fakeIssuesDb.map { it.toUiItem() }

    @Test
    fun issueGameViewModel_initUiState_initGameQuestion() {
        runTest {
            val theme = FakeSource.fakeIssuesDb[0].theme
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeIssues),
                theme = theme,
            )
            val actualGameQuestion = viewModel.uiState.first().gameQuestion
            assert(actualGameQuestion.toIssue(theme) in fakeIssues)
        }
    }

    @Test
    fun issueGameViewModel_initUiState_initFieldIsError() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeIssues),
            )
            val actualValueIsErrorField = viewModel.uiState.first().isError
            assert(!actualValueIsErrorField)
        }
    }

    @Test
    fun issueGameViewModel_initUiStateWithError_initFieldIsError() {
        runTest {
            val viewModel = initViewModel(RequestResult.Error())
            val actualValueIsErrorField = viewModel.uiState.first().isError
            assert(actualValueIsErrorField)
        }
    }

    @Test
    fun issueGameViewModel_updateUiState_gameQuestionUpdated() { // sometimes failed because updateUiState() contains random()
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeIssues)
            )
            val oldGameQuestion = viewModel.uiState.first().gameQuestion
            viewModel.updateUiState()
            val actualGameQuestion = viewModel.uiState.first().gameQuestion
            assertNotEquals(oldGameQuestion, actualGameQuestion)
        }
    }

    @Test
    fun issueGameViewModel_updateUiState_countUpdated() {
        runTest {
            val viewModel = initViewModel(RequestResult.Success(fakeIssues))
            val oldCount = viewModel.uiState.first().score
            viewModel.updateUiState()
            val actualCount = viewModel.uiState.first().score
            assertNotEquals(oldCount, actualCount)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Issue>>,
        theme: String = FakeSource.fakeIssuesDb[0].theme,
    ): IssueGameViewModel {
        return IssueGameViewModel(
            issueRepository = FakeIssueRepository(requestResult),
            theme = theme,
        )
    }
}
