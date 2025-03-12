package com.viktoriagavrosh.englishsimulator.ui.features.goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.StatisticRepository
import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.model.Goal
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve item from repository and manager
 *
 * @param date today date
 * @param repository instance of [StatisticRepository]
 * @param manager instance of [PreferencesManager]
 */
class GoalViewModel(
    private val date: String,
    private val repository: StatisticRepository,
    private val manager: PreferencesManager,
) : ViewModel() {

    val dayStatisticState = repository.getStatisticByDate(date)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = RequestResult.Loading()
        )

    val goalState = manager.getGoal().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = Goal()
    )
}
