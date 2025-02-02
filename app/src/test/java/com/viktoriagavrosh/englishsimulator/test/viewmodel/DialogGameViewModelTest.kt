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

class DialogGameViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeDialogs = FakeSource.fakeDialogsDb.map { it.toQuestion() }

    @Test
    fun dialogGameViewModel_initUiState_initGameQuestion() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeDialogs),
            )
            val actualGameQuestion = viewModel.uiState.first()
                .gameQuestion
                .toQuestion(isToEnglish = false, modelName = ModelName.Dialog)
            assert(actualGameQuestion in fakeDialogs)
        }
    }

    @Test
    fun dialogGameViewModel_initUiState_initFieldIsError() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeDialogs),
            )
            val actualValueIsErrorField = viewModel.uiState.first().isError
            assert(!actualValueIsErrorField)
        }
    }

    @Test
    fun dialogGameViewModel_initUiStateWithError_initFieldIsError() {
        runTest {
            val viewModel = initViewModel(RequestResult.Error())
            val actualValueIsErrorField = viewModel.uiState.first().isError
            assert(actualValueIsErrorField)
        }
    }

    @Test
    fun dialogGameViewModel_updateUiState_gameQuestionUpdated() {
        // sometimes failed because updateUiState() contains random()
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeDialogs)
            )
            val oldGameQuestion = viewModel.uiState.first().gameQuestion
            viewModel.updateUiState()
            val actualGameQuestion = viewModel.uiState.first().gameQuestion
            assertNotEquals(oldGameQuestion, actualGameQuestion)
        }
    }

    @Test
    fun translateGameViewModel_updateUiState_countUpdated() {
        runTest {
            val viewModel = initViewModel(RequestResult.Success(fakeDialogs))
            val oldCount = viewModel.uiState.first().score
            viewModel.updateUiState()
            val actualCount = viewModel.uiState.first().score
            assertNotEquals(oldCount, actualCount)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Question>>,
    ): GameViewModel {
        return GameViewModel(
            useCase = FakeGetQuestionsUseCase(requestResult),
        )
    }
}

