package com.viktoriagavrosh.englishsimulator.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * provide data from data source
 */
interface PreferencesRepository {

    /**
     * Retrieve item from given data source
     *
     * @return flow of [String]
     */
    fun getDate(): Flow<String>

    /**
     * Update value of item from given data source
     *
     * @param date new value
     */
    suspend fun updateDate(date: String)
}

/**
 * provide data from DataStore
 *
 * @param dataStore instance of DataStore<Preferences>
 */
class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : PreferencesRepository {

    val DATE_KEY = stringPreferencesKey("date_key")

    /**
     * Retrieve date from DataStore
     *
     * @return flow of [String]
     */
    override fun getDate(): Flow<String> {
        return dataStore.data
            .catch { emit(emptyPreferences()) }
            .map { preferences ->
                preferences[DATE_KEY] ?: ""
            }
    }

    /**
     * Update value of date from DataStore
     *
     * @param date new value
     */
    override suspend fun updateDate(date: String) {
        dataStore.edit { preferences ->
            preferences[DATE_KEY] = date
        }
    }
}
