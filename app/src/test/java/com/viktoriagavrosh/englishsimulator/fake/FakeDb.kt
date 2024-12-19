package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.DialogDao
import com.viktoriagavrosh.englishsimulator.data.database.IssueDao
import com.viktoriagavrosh.englishsimulator.data.database.SentenceDao
import com.viktoriagavrosh.englishsimulator.model.DialogDb
import com.viktoriagavrosh.englishsimulator.model.IssueDb
import com.viktoriagavrosh.englishsimulator.model.SentenceDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeDb : AppDatabase {
    override fun sentenceDao(): SentenceDao = FakeSentenceDao()
    override fun issueDao(): IssueDao = FakeIssueDao()
    override fun dialogDao(): DialogDao = FakeDialogDao()
}

private class FakeSentenceDao : SentenceDao {

    val sentences = FakeSource.fakeSentencesDb.toMutableList()

    override fun getAllSentences(): Flow<List<SentenceDb>> {
        return flow {
            emit(sentences)
        }
    }

    override suspend fun insert(sentenceDb: SentenceDb) {
        sentences.add(sentenceDb)
    }
}

private class FakeIssueDao : IssueDao {

    val issues = FakeSource.fakeIssuesDb.toMutableList()

    override fun getAllIssues(): Flow<List<IssueDb>> {
        return flow {
            emit(issues)
        }
    }

    override fun getAllThemes(): Flow<List<String>> {
        val themes = issues.map { it.theme }.distinct()
        return flow { emit(themes) }
    }

    override fun getAllIssuesByTheme(theme: String): Flow<List<IssueDb>> {
        val result = issues.filter { it.theme == theme }
        if (result.isEmpty()) throw IllegalArgumentException()
        return flow { emit(result) }
    }

    override suspend fun insert(issueDb: IssueDb) {
        issues.add(issueDb)
    }
}

private class FakeDialogDao : DialogDao {
    override fun getAllDialogs(): Flow<List<DialogDb>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(dialogDb: DialogDb) {
        TODO("Not yet implemented")
    }

}
