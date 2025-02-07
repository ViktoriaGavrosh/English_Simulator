package com.viktoriagavrosh.englishsimulator.ui.navigation

import androidx.lifecycle.ViewModel
import com.viktoriagavrosh.englishsimulator.data.StatisticRepository

/**
 * ViewModel to retrieve and update item from repository dataStore
 *
 * @param repository instance of [StatisticRepository]
 */
class StatisticViewModel(
    dataStoreDate: String,
    private val repository: StatisticRepository
) : ViewModel() {

}
