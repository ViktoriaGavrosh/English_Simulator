package com.viktoriagavrosh.englishsimulator.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile

/**
 * Constant for DatastorePreferences
 */
const val USER_PREFERENCES_NAME = "user_preferences"

/**
 *  Function build DataStore object
 *
 *  @param context local context
 *  @return [DataStore] object
 */
fun getDataStore(context: Context): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(USER_PREFERENCES_NAME)
    }
}
