package com.viktoriagavrosh.englishsimulator.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.viktoriagavrosh.englishsimulator.model.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * provide data from data source
 */
interface PreferencesManager {

    /**
     * Retrieve item from given data source
     *
     * @return flow of [Goal]
     */
    fun getGoal(): Flow<Goal>

    /**
     * Update value of item from given data source
     *
     * @param score new value
     */
    suspend fun updateTranslateGoal(score: Int)

    /**
     * Update value of item from given data source
     *
     * @param score new value
     */
    suspend fun updateIssueGoal(score: Int)

    /**
     * Update value of item from given data source
     *
     * @param score new value
     */
    suspend fun updateDialogGoal(score: Int)

    /**
     * Update value of item from given data source
     *
     * @param score new value
     */
    suspend fun updateWordGoal(score: Int)
}

val TRANSLATE_GOAL_KEY = intPreferencesKey("translate_goal_key")
val ISSUE_GOAL_KEY = intPreferencesKey("issue_goal_key")
val DIALOG_GOAL_KEY = intPreferencesKey("dialog_goal_key")
val WORD_GOAL_KEY = intPreferencesKey("word_goal_key")

/**
 * provide data from DataStore
 *
 * @param dataStore instance of DataStore<Preferences>
 */
class UserPreferencesManager(
    private val dataStore: DataStore<Preferences>
) : PreferencesManager {

    /**
     * Retrieve [Goal] from DataStore
     *
     * @return flow of [Goal]
     */
    override fun getGoal(): Flow<Goal> {
        return dataStore.data
            .catch { emit(emptyPreferences()) }
            .map { preferences ->
                Goal(
                    translateGoal = preferences[TRANSLATE_GOAL_KEY] ?: 0,
                    issueGoal = preferences[ISSUE_GOAL_KEY] ?: 0,
                    dialogGoal = preferences[DIALOG_GOAL_KEY] ?: 0,
                    wordGoal = preferences[WORD_GOAL_KEY] ?: 0
                )
            }
    }

    /**
     * Update value of translateGoal from DataStore
     *
     * @param score new value
     */
    override suspend fun updateTranslateGoal(score: Int) {
        dataStore.edit { preferences ->
            preferences[TRANSLATE_GOAL_KEY] = score
        }
    }

    /**
     * Update value of issueGoal from DataStore
     *
     * @param score new value
     */
    override suspend fun updateIssueGoal(score: Int) {
        dataStore.edit { preferences ->
            preferences[ISSUE_GOAL_KEY] = score
        }
    }

    /**
     * Update value of dialogGoal from DataStore
     *
     * @param score new value
     */
    override suspend fun updateDialogGoal(score: Int) {
        dataStore.edit { preferences ->
            preferences[DIALOG_GOAL_KEY] = score
        }
    }

    /**
     * Update value of wordGoal from DataStore
     *
     * @param score new value
     */
    override suspend fun updateWordGoal(score: Int) {
        dataStore.edit { preferences ->
            preferences[WORD_GOAL_KEY] = score
        }
    }
}
