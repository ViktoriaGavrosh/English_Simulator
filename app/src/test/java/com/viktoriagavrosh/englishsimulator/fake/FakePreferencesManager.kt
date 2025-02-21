package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakePreferencesManager : PreferencesManager {

    private var fakeDate = "02-02-2000"

    override fun getDate(): Flow<String> {
        return flow { emit(fakeDate) }
    }

    override suspend fun updateDate(date: String) {
        fakeDate = date
    }
}
