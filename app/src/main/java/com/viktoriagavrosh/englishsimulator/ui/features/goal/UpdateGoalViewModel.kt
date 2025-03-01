package com.viktoriagavrosh.englishsimulator.ui.features.goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.model.Goal
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from manager
 *
 * @param manager instance of [PreferencesManager]
 */
class UpdateGoalViewModel(
    private val manager: PreferencesManager,
) : ViewModel() {

    val goalsUiState = try {
        manager.getGoal()
    } catch (e: Exception) {
        flow { emit(Goal()) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = Goal()
    )

    fun updateTranslateGoal(score: Int) {
        viewModelScope.launch {
            manager.updateTranslateGoal(score = score)
        }
    }

    fun updateIssueGoal(score: Int) {
        viewModelScope.launch {
            manager.updateIssueGoal(score = score)
        }
    }

    fun updateDialogGoal(score: Int) {
        viewModelScope.launch {
            manager.updateDialogGoal(score = score)
        }
    }

    fun updateWordGoal(score: Int) {
        viewModelScope.launch {
            manager.updateWordGoal(score = score)
        }
    }
}
