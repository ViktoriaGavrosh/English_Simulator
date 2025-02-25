package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.model.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakePreferencesManager : PreferencesManager {

    private var fakeGoal = FakeSource.fakeGoal

    override fun getGoal(): Flow<Goal> {
        return flow { emit(fakeGoal) }
    }

    override suspend fun updateTranslateGoal(score: Int) {
        fakeGoal = fakeGoal.copy(translateGoal = score)
    }

    override suspend fun updateIssueGoal(score: Int) {
        fakeGoal = fakeGoal.copy(issueGoal = score)
    }

    override suspend fun updateDialogGoal(score: Int) {
        fakeGoal = fakeGoal.copy(dialogGoal = score)
    }

    override suspend fun updateWordGoal(score: Int) {
        fakeGoal = fakeGoal.copy(wordGoal = score)
    }
}
