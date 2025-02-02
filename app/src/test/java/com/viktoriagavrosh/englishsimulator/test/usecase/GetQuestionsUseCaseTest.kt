package com.viktoriagavrosh.englishsimulator.test.usecase

import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.GetQuestionsUseCase
import com.viktoriagavrosh.englishsimulator.data.LocalGetQuestionsUseCase
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

class GetQuestionsUseCaseTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private fun initUseCase(repository: GameRepository): GetQuestionsUseCase {
        return LocalGetQuestionsUseCase(
            repository = repository,
        )
    }

    @Test
    fun getQuestionsUseCase_getAllItems_returnListQuestionsFromTranslateRepository() {
        runTest {
            val expectedList =
                FakeSource.fakeSentencesDb.map { it.toQuestion() as Question.Sentence }
            val repository = FakeTranslateRepository(RequestResult.Success(expectedList))
            val useCase = initUseCase(repository)
            val actualList = useCase.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun getQuestionsUseCase_getAllItems_returnListQuestionsFromIssueRepository() {
        runTest {
            val expectedList = FakeSource.fakeIssuesDb.map { it.toQuestion() as Question.Issue }
            val repository = FakeIssueRepository(RequestResult.Success(expectedList))
            val useCase = initUseCase(repository)
            val actualList = useCase.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun getQuestionsUseCase_getAllItems_returnListQuestionsFromDialogRepository() {
        runTest {
            val expectedList = FakeSource.fakeDialogsDb.map { it.toQuestion() as Question.Dialog }
            val repository = FakeDialogRepository(RequestResult.Success(expectedList))
            val useCase = initUseCase(repository)
            val actualList = useCase.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun getQuestionsUseCase_getAllItems_returnListQuestionsFromWordRepository() {
        runTest {
            val expectedList = FakeSource.fakeWordsDb.map { it.toQuestion() as Question.Word }
            val repository = FakeWordRepository(RequestResult.Success(expectedList))
            val useCase = initUseCase(repository)
            val actualList = useCase.getAllItems()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun getQuestionsUseCase_getAllItemsByTheme_returnListQuestionsFromIssueRepository() {
        runTest {
            val theme = FakeSource.fakeIssuesDb[2].theme
            val result = FakeSource.fakeIssuesDb.map { it.toQuestion() as Question.Issue }
            val repository = FakeIssueRepository(RequestResult.Success(result))
            val useCase = initUseCase(repository)
            val expectedList = result.filter { it.theme == theme }
            val actualList = useCase.getAllItemsByTheme(theme = theme)
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun getQuestionsUseCase_getAllItemsByTheme_returnListQuestionsFromWordRepository() {
        runTest {
            val theme = FakeSource.fakeWordsDb[2].theme
            val result = FakeSource.fakeWordsDb.map { it.toQuestion() as Question.Word }
            val repository = FakeWordRepository(RequestResult.Success(result))
            val useCase = initUseCase(repository)
            val expectedList = result.filter { it.theme == theme }
            val actualList = useCase.getAllItemsByTheme(theme = theme)
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }
}
