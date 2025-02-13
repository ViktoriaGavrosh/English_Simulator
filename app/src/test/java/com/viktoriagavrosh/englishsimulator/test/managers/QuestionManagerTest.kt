package com.viktoriagavrosh.englishsimulator.test.managers

import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.QuestionManager
import com.viktoriagavrosh.englishsimulator.data.LocalQuestionManager
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeDialogRepository
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeIssueRepository
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeTranslateRepository
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeWordRepository
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toQuestion
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class QuestionManagerTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private fun initManager(repository: GameRepository): QuestionManager {
        return LocalQuestionManager(
            repository = repository,
        )
    }

    @Test
    fun questionManager_getAllItems_returnListQuestionsFromTranslateRepository() {
        runTest {
            val expectedList =
                FakeSource.fakeSentencesDb.map { it.toQuestion() as Question.Sentence }
            val repository = FakeTranslateRepository(RequestResult.Success(expectedList))
            val manager = initManager(repository)
            val actualList = manager.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun questionManager_getAllItems_returnListQuestionsFromIssueRepository() {
        runTest {
            val expectedList = FakeSource.fakeIssuesDb.map { it.toQuestion() as Question.Issue }
            val repository = FakeIssueRepository(RequestResult.Success(expectedList))
            val manager = initManager(repository)
            val actualList = manager.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun questionManager_getAllItems_returnListQuestionsFromDialogRepository() {
        runTest {
            val expectedList = FakeSource.fakeDialogsDb.map { it.toQuestion() as Question.Dialog }
            val repository = FakeDialogRepository(RequestResult.Success(expectedList))
            val manager = initManager(repository)
            val actualList = manager.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun questionManager_getAllItems_returnListQuestionsFromWordRepository() {
        runTest {
            val expectedList = FakeSource.fakeWordsDb.map { it.toQuestion() as Question.Word }
            val repository = FakeWordRepository(RequestResult.Success(expectedList))
            val manager = initManager(repository)
            val actualList = manager.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun questionManager_getAllItemsByTheme_returnListQuestionsFromIssueRepository() {
        runTest {
            val theme = FakeSource.fakeIssuesDb[2].theme
            val result = FakeSource.fakeIssuesDb.map { it.toQuestion() as Question.Issue }
            val repository = FakeIssueRepository(RequestResult.Success(result))
            val manager = initManager(repository)
            val expectedList = result.filter { it.theme == theme }
            val actualList = manager.getAllItemsByTheme(theme = theme)
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun questionManager_getAllItemsByTheme_returnListQuestionsFromWordRepository() {
        runTest {
            val theme = FakeSource.fakeWordsDb[2].theme
            val result = FakeSource.fakeWordsDb.map { it.toQuestion() as Question.Word }
            val repository = FakeWordRepository(RequestResult.Success(result))
            val manager = initManager(repository)
            val expectedList = result.filter { it.theme == theme }
            val actualList = manager.getAllItemsByTheme(theme = theme)
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }
}
