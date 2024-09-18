package com.viktoriagavrosh.englishsimulator

import com.viktoriagavrosh.englishsimulator.fake.FakeRepeatRepository
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.ui.screens.repeat.RepeatViewModel
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RepeatViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun repeatViewModel_initUiState_initSentence() {
        runTest {
            val viewModel = initViewModel(RequestResult.Success(FakeSource.fakeSentences))
            val uiStateSentence = viewModel.uiState.first().sentence
            assert(uiStateSentence in FakeSource.fakeSentences)
        }
    }

    @Test
    fun repeatViewModel_initUiState_initIsErrorField() {
        runTest {
            val viewModel = initViewModel(RequestResult.Success(FakeSource.fakeSentences))
            val expectedValue = false
            val actualValue = viewModel.uiState.first().isError
            assertEquals(expectedValue, actualValue)
        }
    }

    @Test
    fun repeatViewModel_initUiStateError_initIsErrorField() {
        runTest {
            val viewModel = initViewModel(RequestResult.Error())
            val expectedValue = true
            val actualValue = viewModel.uiState.first().isError
            assertEquals(expectedValue, actualValue)
        }
    }

    @Test
    fun repeatViewModel_updateSentence_sentenceUpdated() {
        runTest {
            val viewModel = initViewModel(RequestResult.Success(FakeSource.fakeSentences))
            val oldSentence = viewModel.uiState.first().sentence
            viewModel.updateSentence()
            val actualSentence = viewModel.uiState.first().sentence
            assert(oldSentence != actualSentence)
        }
    }

    @Test
    fun repeatViewModel_updateSentence_countUpdated() {
        runTest {
            val viewModel = initViewModel(RequestResult.Success(FakeSource.fakeSentences))
            val oldCount = viewModel.count.first()
            viewModel.updateSentence()
            val actualCount = viewModel.count.first()
            assert(oldCount != actualCount)
        }
    }

    private fun initViewModel(requestResult: RequestResult<List<Sentence>>): RepeatViewModel {
        return RepeatViewModel(
            FakeRepeatRepository(requestResult)
        )
    }
}
