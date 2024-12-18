package com.viktoriagavrosh.englishsimulator.test.repository

import com.viktoriagavrosh.englishsimulator.data.LocalIssueRepository
import com.viktoriagavrosh.englishsimulator.fake.FakeDb
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toIssue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class IssueRepositoryTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val repository = LocalIssueRepository(FakeDb())

    @Test
    fun issueRepository_getAllIssue_returnListIssue() {
        runTest {
            val expectedList = FakeSource.fakeIssuesDb.map { it.toIssue() }
            val actualList = repository.getAllIssue()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun issueRepository_getAllIssue_returnRequestResultSuccess() {
        runTest {
            val isSuccess = repository.getAllIssue()
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }

    @Test
    fun issueRepository_getAllIssueByTheme_returnListIssue() {
        runTest {
            val theme = FakeSource.fakeIssuesDb[0].theme
            val expectedList = FakeSource.fakeIssuesDb
                .filter { it.theme == theme}
                .map { it.toIssue() }
            val actualList = repository.getAllIssueByTheme(theme = theme)
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun issueRepository_getAllIssueByTheme_returnRequestResultSuccess() {
        runTest {
            val theme = FakeSource.fakeIssuesDb[0].theme
            val isSuccess = repository.getAllIssueByTheme(theme = theme)
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }

    @Test
    fun issueRepository_getAllIssueByTheme_returnRequestResultError() {
        runTest {
            val theme = "no"
            val isError = repository.getAllIssueByTheme(theme = theme)
                .first() is RequestResult.Error
            assert(isError)
        }
    }

    @Test
    fun issueRepository_getAllThemes_returnListString() {
        runTest {
            val expectedList = FakeSource.fakeIssuesDb.map { it.theme }.distinct()
            val actualList = repository.getAllThemes()
                .first().data ?: emptyList()

            assertEquals(
                expectedList,
                actualList
            )
        }
    }

    @Test
    fun issueRepository_getAllThemes_returnRequestResultSuccess() {
        runTest {
            val isSuccess = repository.getAllThemes()
                .first() is RequestResult.Success

            assert(isSuccess)
        }
    }
}
