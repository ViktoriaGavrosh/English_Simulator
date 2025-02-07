package com.viktoriagavrosh.englishsimulator.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.viktoriagavrosh.englishsimulator.data.PreferencesRepository
import com.viktoriagavrosh.englishsimulator.data.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

private const val TEST_DATASTORE_NAME: String = "test_datastore"

class UserPreferencesRepositoryTest {

    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var repository: PreferencesRepository

    @Before
    fun createDataStore() {
        val testContext: Context = ApplicationProvider.getApplicationContext()
        dataStore = PreferenceDataStoreFactory.create(
            produceFile = { testContext.preferencesDataStoreFile(TEST_DATASTORE_NAME) }
        )
        repository = UserPreferencesRepository(dataStore = dataStore)
    }

    @Test
    fun preferencesRepository_getDate_returnString() = runBlocking {
        val expectedDate = "05-12-2023"
        addPreferencesToDataStore(date = expectedDate)
        val actualDate = repository.getDate().first()
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun preferencesRepository_updateDate_dateUpdated() = runBlocking {
        addPreferencesToDataStore()
        val oldDate = repository.getDate().first()
        repository.updateDate("22-22-2222")
        val newDate = repository.getDate().first()
        assertNotEquals(oldDate, newDate)
    }

    private suspend fun addPreferencesToDataStore(date: String = "11-11-1111") {
        dataStore.edit { preferences ->
            preferences[(repository as UserPreferencesRepository).DATE_KEY] = date
        }
    }

}
