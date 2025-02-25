package com.viktoriagavrosh.englishsimulator.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.viktoriagavrosh.englishsimulator.data.datastore.DIALOG_GOAL_KEY
import com.viktoriagavrosh.englishsimulator.data.datastore.ISSUE_GOAL_KEY
import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.data.datastore.TRANSLATE_GOAL_KEY
import com.viktoriagavrosh.englishsimulator.data.datastore.UserPreferencesManager
import com.viktoriagavrosh.englishsimulator.data.datastore.WORD_GOAL_KEY
import com.viktoriagavrosh.englishsimulator.model.Goal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class UserPreferencesManagerTest {

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val teatScope = TestScope(testDispatcher + Job())

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = teatScope,
        produceFile = { tmpFolder.newFile("user.preferences_pb") }
    )
    private val manager: PreferencesManager = UserPreferencesManager(dataStore = dataStore)

    @Test
    fun preferencesManager_getGoal_returnGoal() = runBlocking {
        val expectedGoal = Goal(
            translateGoal = 5,
            issueGoal = 6,
            dialogGoal = 7,
            wordGoal = 8,
        )
        addPreferencesToDataStore(
            translateGoal = expectedGoal.translateGoal,
            issueGoal = expectedGoal.issueGoal,
            dialogGoal = expectedGoal.dialogGoal,
            wordGoal = expectedGoal.wordGoal,
        )
        val actualGoal = manager.getGoal().first()
        assertEquals(expectedGoal, actualGoal)
    }

    @Test
    fun preferencesManager_updateTranslateGoal_translateGoalUpdated() = runBlocking {
        addPreferencesToDataStore()
        val oldGoal = manager.getGoal().first().translateGoal
        manager.updateTranslateGoal(score = 12)
        val newGoal = manager.getGoal().first().translateGoal
        assertNotEquals(oldGoal, newGoal)
    }

    @Test
    fun preferencesManager_updateIssueGoal_issueGoalUpdated() = runBlocking {
        addPreferencesToDataStore()
        val oldGoal = manager.getGoal().first().issueGoal
        manager.updateIssueGoal(score = 15)
        val newGoal = manager.getGoal().first().issueGoal
        assertNotEquals(oldGoal, newGoal)
    }

    @Test
    fun preferencesManager_updateDialogGoal_dialogGoalUpdated() = runBlocking {
        addPreferencesToDataStore()
        val oldGoal = manager.getGoal().first().dialogGoal
        manager.updateDialogGoal(score = 8)
        val newGoal = manager.getGoal().first().dialogGoal
        assertNotEquals(oldGoal, newGoal)
    }

    @Test
    fun preferencesManager_updateWordGoal_wordGoalUpdated() = runBlocking {
        addPreferencesToDataStore()
        val oldGoal = manager.getGoal().first().wordGoal
        manager.updateWordGoal(score = 17)
        val newGoal = manager.getGoal().first().wordGoal
        assertNotEquals(oldGoal, newGoal)
    }

    private suspend fun addPreferencesToDataStore(
        translateGoal: Int = 1,
        issueGoal: Int = 1,
        dialogGoal: Int = 1,
        wordGoal: Int = 1,
    ) {
        dataStore.edit { preferences ->
            preferences[TRANSLATE_GOAL_KEY] = translateGoal
            preferences[ISSUE_GOAL_KEY] = issueGoal
            preferences[DIALOG_GOAL_KEY] = dialogGoal
            preferences[WORD_GOAL_KEY] = wordGoal
        }
    }

}
