package com.viktoriagavrosh.englishsimulator.test.repository

import com.viktoriagavrosh.englishsimulator.data.LocalDialogRepository
import com.viktoriagavrosh.englishsimulator.fake.FakeDb
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DialogRepositoryTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val repository = LocalDialogRepository(FakeDb())

    @Test
    fun dialogRepository_getAllDialogs_returnListDialogs() {
        runTest {
            val expectedList = FakeSource.fakeDialogsDb.map { it.toDialog() }
            val actualList = repository.getAllDialogs()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun dialogRepository_getAllDialogs_returnRequestResultSuccess() {
        runTest {
            val isSuccess = repository.getAllDialogs()
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }
}
