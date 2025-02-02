package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeGetQuestionsUseCase
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.model.ModelName
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameViewModel
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toQuestion
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class IssueGameViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeIssues = FakeSource.fakeIssuesDb.map { it.toQuestion() }

    @Test
    fun issueGameViewModel_initUiState_initGameQuestion() {
        runTest {
            val theme = FakeSource.fakeIssuesDb[0].theme
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeIssues),
                theme = theme,
            )
            val actualGameQuestion = viewModel.uiState.first()
                .gameQuestion
                .toQuestion(isToEnglish = false, theme = theme, modelName = ModelName.Issue)
            assert(actualGameQuestion in fakeIssues)
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
    fun issueGameViewModel_updateUiState_gameQuestionUpdated() {
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
        requestResult: RequestResult<List<Question>>,
        theme: String = FakeSource.fakeIssuesDb[0].theme,
    ): GameViewModel {
        return GameViewModel(
            useCase = FakeGetQuestionsUseCase(requestResult),
            theme = theme,
        )
    }
}
