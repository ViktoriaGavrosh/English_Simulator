package com.viktoriagavrosh.englishsimulator.test.repository

import com.viktoriagavrosh.englishsimulator.data.LocalWordRepository
import com.viktoriagavrosh.englishsimulator.fake.FakeDb
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.model.QuizName
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toUiItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class WordRepositoryTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val repository = LocalWordRepository(FakeDb())

    @Test
    fun wordRepository_getAllWords_returnListWord() {
        runTest {
            val expectedList = FakeSource.fakeWordsDb.map { it.toUiItem() }
            val actualList = repository.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun wordRepository_getAllWords_returnRequestResultSuccess() {
        runTest {
            val isSuccess = repository.getAllItems()
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }

    @Test
    fun wordRepository_getAllWordsByTheme_returnListWord() {
        runTest {
            val theme = FakeSource.fakeWordsDb[0].theme
            val expectedList = FakeSource.fakeWordsDb
                .filter { it.theme == theme }
                .map { it.toUiItem() }
            val actualList = repository.getAllItemsByTheme(theme = theme)
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun wordRepository_getAllWordsByTheme_returnRequestResultSuccess() {
        runTest {
            val theme = FakeSource.fakeWordsDb[0].theme
            val isSuccess = repository.getAllItemsByTheme(theme = theme)
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }

    @Test
    fun wordRepository_getAllWordsByTheme_returnRequestResultError() {
        runTest {
            val theme = "no"
            val isError = repository.getAllItemsByTheme(theme = theme)
                .first() is RequestResult.Error
            assert(isError)
        }
    }

    @Test
    fun wordRepository_getAllThemes_returnListString() {
        runTest {
            val expectedList = FakeSource.fakeWordsDb.map { it.theme }.distinct()
            val actualList = repository.getAllThemes()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun wordRepository_getAllThemes_returnRequestResultSuccess() {
        runTest {
            val isSuccess = repository.getAllThemes()
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }

    @Test
    fun wordRepository_getWordById_returnWord() {
        runTest {
            val id = FakeSource.fakeWordsDb[2].id
            val expected = FakeSource.fakeWordsDb.first { it.id == id }.toUiItem()
            val actual = repository.getWordById(id)
                .first().data ?: UiItem()

            assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun wordRepository_getWordById_returnRequestResultSuccess() {
        runTest {
            val id = FakeSource.fakeWordsDb[2].id
            val isSuccess = repository.getWordById(id)
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }

    @Test
    fun wordRepository_getWordById_returnRequestResultError() {
        runTest {
            val id = 136
            val isError = repository.getWordById(id)
                .first() is RequestResult.Error
            assert(isError)
        }
    }

    @Test
    fun wordRepository_insertWord_newWordInsert() {
        runTest {
            val id = 125
            val expected = UiItem(id = id, quizName = QuizName.Word)
            repository.insertWord(expected)
            val actual = repository.getWordById(id)
                .first().data ?: UiItem()
            repository.deleteWord(expected)
            assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun wordRepository_updateWord_newWordUpdated() {
        runTest {
            val newText = "newText"
            val expected = FakeSource.fakeWordsDb[0].copy(englishWord = newText).toUiItem()
            repository.updateWord(expected)
            val actual = repository.getWordById(expected.id)
                .first().data ?: UiItem()
            assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun wordRepository_deleteWord_newWordDeleted() {
        runTest {
            val id = 125
            val expected = UiItem(id = id, quizName = QuizName.Word)
            repository.insertWord(expected)
            val actual = repository.getWordById(id)
                .first().data ?: UiItem()
            assertEquals(
                expected,
                actual
            )
            repository.deleteWord(expected)
            val isError = repository.getWordById(id)
                .first() is RequestResult.Error
            assert(isError)
        }
    }
}
