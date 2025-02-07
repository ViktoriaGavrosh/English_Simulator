package com.viktoriagavrosh.englishsimulator.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.data.datastore.UserPreferencesManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

private const val TEST_DATASTORE_NAME: String = "test_datastore"

class UserPreferencesManagerTest {

    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var manager: PreferencesManager

    @Before
    fun createDataStore() {
        val testContext: Context = ApplicationProvider.getApplicationContext()
        dataStore = PreferenceDataStoreFactory.create(
            produceFile = { testContext.preferencesDataStoreFile(TEST_DATASTORE_NAME) }
        )
        manager = UserPreferencesManager(dataStore = dataStore)
    }

    @Test
    fun preferencesManager_getDate_returnString() = runBlocking {
        val expectedDate = "05-12-2023"
        addPreferencesToDataStore(date = expectedDate)
        val actualDate = manager.getDate().first()
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun preferencesManager_updateDate_dateUpdated() = runBlocking {
        addPreferencesToDataStore()
        val oldDate = manager.getDate().first()
        manager.updateDate("22-22-2222")
        val newDate = manager.getDate().first()
        assertNotEquals(oldDate, newDate)
    }

    private suspend fun addPreferencesToDataStore(date: String = "11-11-1111") {
        dataStore.edit { preferences ->
            preferences[(manager as UserPreferencesManager).DATE_KEY] = date
        }
    }

}
