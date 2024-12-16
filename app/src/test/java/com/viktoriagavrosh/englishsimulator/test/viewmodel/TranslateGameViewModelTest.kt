package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeTranslateRepository
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateGameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.model.toSentence
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toSentence
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class TranslateGameViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeSentences = FakeSource.fakeSentencesDb.map { it.toSentence() }

    @Test
    fun translateGameViewModel_initUiState_initGameQuestion() {
        runTest {
            val isToEnglish = true
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeSentences),
                isToEnglish = isToEnglish,
            )
            val actualGameQuestion = viewModel.uiState.first().gameQuestion
            assert(actualGameQuestion.toSentence(isToEnglish) in fakeSentences)
        }
    }

    @Test
    fun translateGameViewModel_initUiState_initFieldIsError() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeSentences),
            )
            val actualValueIsErrorField = viewModel.uiState.first().isError
            assert(!actualValueIsErrorField)
        }
    }

    @Test
    fun translateGameViewModel_initUiStateWithError_initFieldIsError() {
        runTest {
            val viewModel = initViewModel(RequestResult.Error())
            val actualValueIsErrorField = viewModel.uiState.first().isError
            assert(actualValueIsErrorField)
        }
    }

    @Test
    fun translateGameViewModel_updateUiState_gameQuestionUpdated() {
        runTest {
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeSentences)
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
            val viewModel = initViewModel(RequestResult.Success(fakeSentences))
            val oldCount = viewModel.uiState.first().score
            viewModel.updateUiState()
            val actualCount = viewModel.uiState.first().score
            assertNotEquals(oldCount, actualCount)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Sentence>>,
        isToEnglish: Boolean = true,
    ): TranslateGameViewModel {
        return TranslateGameViewModel(
            translateRepository = FakeTranslateRepository(requestResult),
            isToEnglish = isToEnglish,
        )
    }
}

