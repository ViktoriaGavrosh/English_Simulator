package com.viktoriagavrosh.englishsimulator.datastore
/*
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

 */
