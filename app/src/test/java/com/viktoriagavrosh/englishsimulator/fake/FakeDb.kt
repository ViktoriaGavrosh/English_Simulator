package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.DialogDao
import com.viktoriagavrosh.englishsimulator.data.database.IssueDao
import com.viktoriagavrosh.englishsimulator.data.database.SentenceDao
import com.viktoriagavrosh.englishsimulator.data.database.StatisticDao
import com.viktoriagavrosh.englishsimulator.data.database.WordDao
import com.viktoriagavrosh.englishsimulator.model.dbmodel.DialogDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.IssueDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.SentenceDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.StatisticDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.WordDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeDb : AppDatabase {
    override fun sentenceDao(): SentenceDao = FakeSentenceDao()
    override fun issueDao(): IssueDao = FakeIssueDao()
    override fun dialogDao(): DialogDao = FakeDialogDao()
    override fun wordDao(): WordDao = FakeWordDao
    override fun statisticDao(): StatisticDao = FakeStatisticDao
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

    val dialogs = FakeSource.fakeDialogsDb.toMutableList()

    override fun getAllDialogs(): Flow<List<DialogDb>> {
        return flow {
            emit(dialogs)
        }
    }

    override suspend fun insert(dialogDb: DialogDb) {
        dialogs.add(dialogDb)
    }
}

private object FakeWordDao : WordDao {

    val words = FakeSource.fakeWordsDb.toMutableList()

    override suspend fun delete(wordDb: WordDb) {
        words.remove(wordDb)
    }

    override fun getAllThemes(): Flow<List<String>> {
        val themes = words.map { it.theme }.distinct()
        return flow { emit(themes) }
    }

    override fun getAllWords(): Flow<List<WordDb>> {
        return flow {
            emit(words)
        }
    }

    override fun getAllWordsByTheme(theme: String): Flow<List<WordDb>> {
        val result = words.filter { it.theme == theme }
        if (result.isEmpty()) throw IllegalArgumentException()
        return flow { emit(result) }
    }

    override fun getWordById(id: Int): Flow<WordDb> {
        val result = words.first { it.id == id }
        return flow { emit(result) }
    }

    override suspend fun insert(wordDb: WordDb) {
        words.add(wordDb)
    }

    override suspend fun update(wordDb: WordDb) {
        val oldWord = words.first { it.id == wordDb.id }
        val index = words.indexOf(oldWord)
        words[index] = wordDb
    }
}

private object FakeStatisticDao : StatisticDao {

    val statistics = FakeSource.fakeStatisticsDb.toMutableList()

    override suspend fun update(statisticDb: StatisticDb) {
        val oldItem = statistics.first { it.id == statisticDb.id }
        val index = statistics.indexOf(oldItem)
        statistics[index] = statisticDb
    }

    override suspend fun insert(statisticDb: StatisticDb) {
        statistics.add(statisticDb)
    }

    override fun getStatisticByDate(date: String): Flow<List<StatisticDb>> {
        val result = statistics.filter { it.date == date }
        if (result.isEmpty()) throw IllegalArgumentException()
        return flow { emit(result) }
    }

    override fun getAllStatisticsByMonth(month: String): Flow<List<StatisticDb>> {
        val result = statistics.filter { it.date.substring(3, 5) == month }
        if (result.isEmpty()) throw IllegalArgumentException()
        return flow { emit(result) }
    }

    override fun getAllMonths(): Flow<List<String>> {
        val months = statistics.map { it.date.substring(3, 5) }.distinct()
        return flow { emit(months) }
    }

    override suspend fun updateTranslateScore(date: String, score: Int) {
        val oldItem = statistics.first { it.date == date }
        val index = statistics.indexOf(oldItem)
        val newItem = oldItem.copy(translateScore = score)
        statistics[index] = newItem
    }

    override suspend fun updateIssueScore(date: String, score: Int) {
        val oldItem = statistics.first { it.date == date }
        val index = statistics.indexOf(oldItem)
        val newItem = oldItem.copy(issueScore = score)
        statistics[index] = newItem
    }

    override suspend fun updateDialogScore(date: String, score: Int) {
        val oldItem = statistics.first { it.date == date }
        val index = statistics.indexOf(oldItem)
        val newItem = oldItem.copy(dialogScore = score)
        statistics[index] = newItem
    }

    override suspend fun updateWordScore(date: String, score: Int) {
        val oldItem = statistics.first { it.date == date }
        val index = statistics.indexOf(oldItem)
        val newItem = oldItem.copy(wordScore = score)
        statistics[index] = newItem
    }

    override suspend fun deleteAllStatisticsByMonth(month: String) {
        statistics.removeAll { it.date.substring(3, 5) == month }
    }
}
