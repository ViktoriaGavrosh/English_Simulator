package com.viktoriagavrosh.englishsimulator

import com.viktoriagavrosh.englishsimulator.data.RepeatScreenRepository
import com.viktoriagavrosh.englishsimulator.fake.FakeDb
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toSentence
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TranslateRepositoryTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val repository = RepeatScreenRepository(FakeDb())

    @Test
    fun translateRepository_getAllSentences_returnListSentences() {
        runTest {
            val expectedList = FakeSource.fakeSentencesDb.map { it.toSentence() }
            val actualList = repository.getAllSentences()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun translateRepository_getAllSentences_returnRequestResultSuccess() {
        runTest {
            val isSuccess = repository.getAllSentences()
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }
}
