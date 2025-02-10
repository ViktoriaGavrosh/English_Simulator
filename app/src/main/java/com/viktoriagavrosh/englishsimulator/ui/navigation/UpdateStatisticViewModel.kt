package com.viktoriagavrosh.englishsimulator.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.StatisticRepository
import com.viktoriagavrosh.englishsimulator.model.Statistic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from repository dataStore
 *
 * @param repository instance of [StatisticRepository]
 */
class UpdateStatisticViewModel(
    private val date: String,
    private val repository: StatisticRepository
) : ViewModel() {

    init {
        prepareDatabase()
    }

    /**
     * Update score of quest "Translate sentences" in data source
     *
     *  @param score new value
     */
    fun updateTranslateScore(score: Int) {
        viewModelScope.launch {
            val oldScore = repository.getStatisticByDate(date).first()
                .data?.firstOrNull()?.translateScore ?: 0
            val newScore = oldScore + score
                repository.updateTranslateScore(date = date, score = newScore)
        }
    }

    /**
     * Update score of quest "Tell about yourself" in data source
     *
     *  @param score new value
     */
    fun updateIssueScore(score: Int) {
        viewModelScope.launch {
            val oldScore = repository.getStatisticByDate(date).first()
                .data?.firstOrNull()?.issueScore ?: 0
            val newScore = oldScore + score
            repository.updateIssueScore(date = date, score = newScore)
        }
    }

    /**
     * Update score of quest "Short dialogs" in data source
     *
     *  @param score new value
     */
    fun updateDialogScore(score: Int) {
        viewModelScope.launch {
            val oldScore = repository.getStatisticByDate(date).first()
                .data?.firstOrNull()?.dialogScore ?: 0
            val newScore = oldScore + score
            repository.updateDialogScore(date = date, score = newScore)
        }
    }

    /**
     * Update score of quest "FlashCards" in data source
     *
     * @param score new value
     */
    fun updateWordScore(score: Int) {
        viewModelScope.launch {
            val oldScore = repository.getStatisticByDate(date).first()
                .data?.firstOrNull()?.wordScore ?: 0
            val newScore = oldScore + score
            repository.updateWordScore(date = date, score = newScore)
        }
    }

    suspend fun getStatisticByDate(date: String): Statistic {   // only for testing
        return repository.getStatisticByDate(date).first().data?.firstOrNull() ?: Statistic()
    }

    private fun prepareDatabase() {
        viewModelScope.launch {
            val isNewDate = repository.getStatisticByDate(date)
                .first().data?.isEmpty() ?: true
            if(isNewDate) {
                repository.insertStatistic(Statistic(date = date))
            }
            if (date.substring(0, 2) == "01") {
                cleanDatabase()
            }
        }
    }

    private fun cleanDatabase() {
        viewModelScope.launch {
            var monthForCleaning = date.substring(3, 5).toInt()
            repeat(2) {
                val month = monthForCleaning - 1
                monthForCleaning = if (month <= 0) 12 else month
            }
            val monthString = monthForCleaning.toString()
            val monthResult = if (monthString.length == 1) "0$monthString" else monthString
            repository.deleteAllStatisticsByMonth(monthResult)
        }
    }
}
