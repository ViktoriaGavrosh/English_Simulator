package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeIssueRepository
import com.viktoriagavrosh.englishsimulator.model.Issue
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuViewModel
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toIssue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class IssueMenuViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeIssues = FakeSource.fakeIssuesDb.map { it.toIssue() }

    @Test
    fun issueMenuViewModel_initUiState_initListTheme() {
        runTest {
            val expectedListTheme = fakeIssues.map { it.theme }.distinct()
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(fakeIssues),
            )
            val actualListTheme = viewModel.uiState.first().data ?: emptyList()
            assertEquals(expectedListTheme, actualListTheme)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Issue>>,
    ): IssueMenuViewModel {
        return IssueMenuViewModel(
            repository = FakeIssueRepository(requestResult),
        )
    }
}
