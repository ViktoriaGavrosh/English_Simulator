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
     */
    fun updateTranslateScore() {
        viewModelScope.launch {
            repository.updateTranslateScore(date = date)
        }
    }

    /**
     * Update score of quest "Tell about yourself" in data source
     *
     */
    fun updateIssueScore() {
        viewModelScope.launch {
            repository.updateIssueScore(date = date)
        }
    }

    /**
     * Update score of quest "Short dialogs" in data source
     *
     */
    fun updateDialogScore() {
        viewModelScope.launch {
            repository.updateDialogScore(date = date)
        }
    }

    /**
     * Update score of quest "FlashCards" in data source
     *
     */
    fun updateWordScore() {
        viewModelScope.launch {
            repository.updateWordScore(date = date)
        }
    }

    suspend fun getStatisticByDate(date: String): Statistic {   // only for testing
        return repository.getStatisticByDate(date).first().data?.firstOrNull() ?: Statistic()
    }

    private fun prepareDatabase() {
        viewModelScope.launch {
            val isNewDate = repository.getStatisticByDate(date)
                .first().data?.isEmpty() ?: true
            if (isNewDate) {
                repository.insertStatistic(Statistic(date = date))
            }
            if (date.substring(0, 2) == "01") {
                cleanDatabase()
            }
        }
    }

    private fun cleanDatabase() {
        viewModelScope.launch {
            val thisMonth = date.substring(3)
            val lastMonth = getLastMonth(thisMonth)
            repository.deleteAllStatisticsByMonth(thisMonth, lastMonth)
        }
    }

    private fun getLastMonth(month: String): String {
        val (monthNum, year) = month.split("-")
        var lastMonth = if (monthNum.toInt() - 1 <= 0) {
            12.toString()
        } else {
            (monthNum.toInt() - 1).toString()
        }
        if (lastMonth.length == 1) lastMonth = "0$lastMonth"
        return "$lastMonth-$year"
    }
}
