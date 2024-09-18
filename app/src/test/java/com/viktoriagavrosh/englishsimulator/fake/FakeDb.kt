package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.SentenceDao
import com.viktoriagavrosh.englishsimulator.model.SentenceDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeDb : AppDatabase {
    override fun sentenceDao(): SentenceDao = FakeDao()

}

private class FakeDao : SentenceDao {

    override fun getAllSentences(): Flow<List<SentenceDb>> {
        return flow {
            emit(FakeSource.fakeSentencesDb)
        }
    }

    override suspend fun insert(sentenceDb: SentenceDb) {
        TODO("Not yet implemented")
    }
}
