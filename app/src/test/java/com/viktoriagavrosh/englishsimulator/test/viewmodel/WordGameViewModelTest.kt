package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeGetQuestionsUseCase
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
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

class WordGameViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeWords = FakeSource.fakeWordsDb.map { it.toQuestion() }

    @Test
    fun wordGameViewModel_initUiState_initGameQuestion() {
        runTest {
            val theme = FakeSource.fakeWordsDb[0].theme
            val isToEnglish = true
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeWords),
                theme = theme,
                isToEnglish = isToEnglish
            )
            val actualGameQuestion = viewModel.uiState.first().gameQuestion
            assert(actualGameQuestion.toQuestion(theme, isToEnglish) in fakeWords)
        }
    }

    @Test
    fun wordGameViewModel_initUiState_initFieldIsError() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeWords),
            )
            val actualValueIsErrorField = viewModel.uiState.first().isError
            assert(!actualValueIsErrorField)
        }
    }

    @Test
    fun wordGameViewModel_initUiStateWithError_initFieldIsError() {
        runTest {
            val viewModel = initViewModel(RequestResult.Error())
            val actualValueIsErrorField = viewModel.uiState.first().isError
            assert(actualValueIsErrorField)
        }
    }

    @Test
    fun wordGameViewModel_updateUiState_gameQuestionUpdated() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeWords)
            )
            val oldGameQuestion = viewModel.uiState.first().gameQuestion
            viewModel.updateUiState()
            val actualGameQuestion = viewModel.uiState.first().gameQuestion
            assertNotEquals(oldGameQuestion, actualGameQuestion)
        }
    }

    @Test
    fun wordGameViewModel_updateUiState_countUpdated() {
        runTest {
            val viewModel = initViewModel(RequestResult.Success(fakeWords))
            val oldCount = viewModel.uiState.first().score
            viewModel.updateUiState()
            val actualCount = viewModel.uiState.first().score
            assertNotEquals(oldCount, actualCount)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Question>>,
        theme: String = FakeSource.fakeWordsDb[0].theme,
        isToEnglish: Boolean = true,
    ): GameViewModel {
        return GameViewModel(
            useCase = FakeGetQuestionsUseCase(requestResult),
            theme = theme,
            isToEnglish = isToEnglish,
        )
    }
}
