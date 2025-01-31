package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeWordRepository
import com.viktoriagavrosh.englishsimulator.model.Word
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordUpdateViewModel
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toUiItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class WordUpdateViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeWords = FakeSource.fakeWordsDb.map { it.toUiItem() }

    @Test
    fun wordUpdateViewModel_initUiState_initWord() {
        runTest {
            val expectedWord = FakeSource.fakeWordsDb[2].toUiItem()
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeWords),
                wordId = expectedWord.id,
            )
            val actualWord = viewModel.uiState.first().word
            assertEquals(expectedWord, actualWord)
        }
    }

    @Test
    fun wordUpdateViewModel_initUiState_initDefaultWord() {
        runTest {
            val expectedWord = Word()
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeWords),
                wordId = 0,
            )
            val actualWord = viewModel.uiState.first().word
            assertEquals(expectedWord, actualWord)
        }
    }

    @Test
    fun wordUpdateViewModel_initUiState_initDefaultWordWithError() {
        runTest {
            val word = FakeSource.fakeWordsDb[2].toUiItem()
            val viewModel = initViewModel(
                requestResult = RequestResult.Error(),
                wordId = word.id,
            )
            val expectedWord = Word()
            val actualWord = viewModel.uiState.first().word
            assertEquals(expectedWord, actualWord)
        }
    }

    @Test
    fun wordUpdateViewModel_initUiState_initFieldIsWordValidTrue() {
        runTest {
            val viewModel = initViewModel(
                wordId = FakeSource.fakeWordsDb[2].toUiItem().id
            )
            val actualValueIsWordValidField = viewModel.uiState.first().isWordValid
            assert(actualValueIsWordValidField)
        }
    }

    @Test
    fun wordUpdateViewModel_initUiState_initFieldIsWordValidFalse() {
        runTest {
            val viewModel = initViewModel(
                wordId = Word().id
            )
            val actualValueIsWordValidField = viewModel.uiState.first().isWordValid
            assert(!actualValueIsWordValidField)
        }
    }

    @Test
    fun wordUpdateViewModel_uiState_fieldIsWordValidUpdated() {
        runTest {
            val viewModel = initViewModel(
                wordId = Word().id
            )
            assert(!viewModel.uiState.first().isWordValid)
            viewModel.updateEnglishText("Text")
            assert(!viewModel.uiState.first().isWordValid)
            viewModel.updateRussianText("Text")
            assert(!viewModel.uiState.first().isWordValid)
            viewModel.updateTheme("Theme")
            assert(viewModel.uiState.first().isWordValid)
        }
    }

    @Test
    fun wordUpdateViewModel_updateEnglishText_uiStateUpdated() {
        runTest {
            val newText = "new text"
            val word = FakeSource.fakeWordsDb[1].toUiItem()
            val viewModel = initViewModel(
                wordId = word.id
            )
            viewModel.updateEnglishText(text = newText)
            val expectedWord = word.copy(questionText = newText)
            val actualWord = viewModel.uiState.first().word
            assertEquals(expectedWord, actualWord)
        }
    }

    @Test
    fun wordUpdateViewModel_updateRussianText_uiStateUpdated() {
        runTest {
            val newText = "new text"
            val word = FakeSource.fakeWordsDb[1].toUiItem()
            val viewModel = initViewModel(
                wordId = word.id
            )
            viewModel.updateRussianText(text = newText)
            val expectedWord = word.copy(answerText = newText)
            val actualWord = viewModel.uiState.first().word
            assertEquals(expectedWord, actualWord)
        }
    }

    @Test
    fun wordUpdateViewModel_updateTheme_uiStateUpdated() {
        runTest {
            val newTheme = "new theme"
            val word = FakeSource.fakeWordsDb[1].toUiItem()
            val viewModel = initViewModel(
                wordId = word.id
            )
            viewModel.updateTheme(text = newTheme)
            val expectedWord = word.copy(theme = newTheme)
            val actualWord = viewModel.uiState.first().word
            assertEquals(expectedWord, actualWord)
        }
    }

    @Test
    fun wordUpdateViewModel_saveWord_updateWord() {
        runTest {
            val newText = "new text"
            val word = FakeSource.fakeWordsDb[1].toUiItem()
            val viewModel = initViewModel(
                wordId = word.id
            )
            viewModel.updateEnglishText(text = newText)
            viewModel.saveWord()
            val expectedWord = word.copy(questionText = newText)
            viewModel.initUiState(wordId = word.id)
            val actualWord = viewModel.uiState.first().word
            assertEquals(expectedWord, actualWord)
        }
    }

    @Test
    fun wordUpdateViewModel_deleteWord_wordDeleted() {
        runTest {
            val word = FakeSource.fakeWordsDb[1].toUiItem()
            val viewModel = initViewModel(
                wordId = word.id
            )
            viewModel.deleteWord()
            viewModel.initUiState(wordId = word.id)
            val actualWord = viewModel.uiState.first().word
            assertEquals(Word(), actualWord)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Word>> = RequestResult.Success(fakeWords),
        wordId: Int = 1,
    ): WordUpdateViewModel {
        return WordUpdateViewModel(
            repository = FakeWordRepository(requestResult),
            wordId = wordId,
        )
    }
}
