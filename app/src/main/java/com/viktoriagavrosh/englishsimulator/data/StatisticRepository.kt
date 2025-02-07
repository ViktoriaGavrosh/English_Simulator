package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.data.datastore.UserPreferencesManager

interface StatisticRepository {
}

class LocalStatisticRepository(
    private val preferencesManager: PreferencesManager
) : StatisticRepository {

}
