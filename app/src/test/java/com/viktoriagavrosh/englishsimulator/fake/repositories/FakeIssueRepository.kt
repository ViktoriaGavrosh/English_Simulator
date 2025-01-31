package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.IssueRepository
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeIssueRepository(
    private val requestResult: RequestResult<List<UiItem>>
) : IssueRepository, GameRepository {
    override fun getAllItems(): Flow<RequestResult<List<UiItem>>> {
        return flow { emit(requestResult) }
    }

    override fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<UiItem>>> {
        val issues = requestResult.map { list ->
            list.filter { it.theme == theme }
        }
        return flow { emit(issues) }
    }

    override fun getAllThemes(): Flow<RequestResult<List<String>>> {
        val themes = requestResult.map { list ->
            list.map { it.theme }.distinct()
        }
        return flow { emit(themes) }
    }
}
