package com.viktoriagavrosh.englishsimulator.test.viewmodel

import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.fake.repositories.FakeIssueRepository
import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuViewModel
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.TestDispatcherRule
import com.viktoriagavrosh.englishsimulator.utils.toQuestion
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class IssueMenuViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val fakeIssues = FakeSource.fakeIssuesDb.map { it.toQuestion() }

    @Test
    fun issueMenuViewModel_initUiState_initListTheme() {
        runTest {
            val expectedListTheme = fakeIssues.map { it.theme }.distinct()
            val newIssues = fakeIssues.map { it as Question.Issue }
            val viewModel = initViewModel(
                requestResult = RequestResult.Success(newIssues)
            )
            val actualListTheme = viewModel.uiState.first().data ?: emptyList()
            assertEquals(expectedListTheme, actualListTheme)
        }
    }

    private fun initViewModel(
        requestResult: RequestResult<List<Question.Issue>>,
    ): IssueMenuViewModel {
        return IssueMenuViewModel(
            repository = FakeIssueRepository(requestResult),
        )
    }
}
