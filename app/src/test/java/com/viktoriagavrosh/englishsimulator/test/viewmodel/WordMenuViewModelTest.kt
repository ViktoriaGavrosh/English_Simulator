package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeWordRepository
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toUiItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class WordMenuViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeWords = FakeSource.fakeWordsDb.map { it.toUiItem() }

    @Test
    fun wordMenuViewModel_initUiState_initListTheme() {
        runTest {
            val expectedListTheme = fakeWords.map { it.theme }.distinct()
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeWords),
            )
            val actualListTheme = viewModel.uiState.first().data ?: emptyList()
            assertEquals(expectedListTheme, actualListTheme)
        }
    }

    @Test
    fun wordMenuViewModel_initSelectedLanguage_initLanguageRuToEn() {
        runTest {
            val expectedLanguageQuest = Quest.RuToEn
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeWords),
            )
            val actualLanguageQuest = viewModel.selectedLanguage.first()
            assertEquals(expectedLanguageQuest, actualLanguageQuest)
        }
    }

    @Test
    fun wordMenuViewModel_updateLanguage_selectedLanguageUpdated() {
        runTest {
            val expectedLanguageQuest = Quest.EnToRu
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeWords),
            )
            viewModel.updateLanguage("На русский")
            val actualLanguageQuest = viewModel.selectedLanguage.first()
            assertEquals(expectedLanguageQuest, actualLanguageQuest)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<UiItem>>,
    ): WordMenuViewModel {
        return WordMenuViewModel(
            repository = FakeWordRepository(requestResult),
        )
    }
}
