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

    fun updateTranslateGoal(score: String) {
        viewModelScope.launch {
            val newScore = score.toIntScore()
            manager.updateTranslateGoal(score = newScore)
        }
    }

    fun updateIssueGoal(score: String) {
        viewModelScope.launch {
            val newScore = score.toIntScore()
            manager.updateIssueGoal(score = newScore)
        }
    }

    fun updateDialogGoal(score: String) {
        viewModelScope.launch {
            val newScore = score.toIntScore()
            manager.updateDialogGoal(score = newScore)
        }
    }

    fun updateWordGoal(score: String) {
        viewModelScope.launch {
            val newScore = score.toIntScore()
            manager.updateWordGoal(score = newScore)
        }
    }
}

private fun String.toIntScore(): Int {
    val newScore = this.toIntOrNull() ?: 0
    return if (newScore < 0) 0 else newScore
}
