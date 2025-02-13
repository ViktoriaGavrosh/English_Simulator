package com.viktoriagavrosh.englishsimulator.ui.features.statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.StatisticRepository
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


/**
 * ViewModel to retrieve and update item from repository data source
 *
 * @param repository instance of [StatisticRepository]
 */
class StatisticViewModel(
    private val repository: StatisticRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(StatisticUiState())

    init {
        initUiState()
    }

    internal val uiState: StateFlow<StatisticUiState>
        get() = _uiState.asStateFlow()

    fun updateUiState(monthIndex: Int) {
        viewModelScope.launch {
            val month = uiState.first().months[monthIndex]
            when (val requestResult = repository.getAllStatisticsByMonth(month).first()) {
                is RequestResult.Success -> {
                    val list = requestResult.data
                    updateStatistics(list)
                }

                is RequestResult.Error -> updateIsError()

                is RequestResult.Loading -> {}
            }

        }
    }

    private fun initUiState() {
        viewModelScope.launch {
            initMonths()
            if (!uiState.first().isError) {
                updateUiState(1)
            }
        }
    }

    private suspend fun initMonths() {
        when (val requestResult = repository.getAllMonths().first()) {
            is RequestResult.Success -> {
                val months = prepareMonths(requestResult.data)
                _uiState.update {
                    it.copy(
                        months = months
                    )
                }
            }

            is RequestResult.Error -> updateIsError()

            is RequestResult.Loading -> {}
        }
    }

    private fun prepareMonths(list: List<Month>): List<Month> {
        return when (list.size) {
            0 -> listOf(Month.EmptyMonth, Month.EmptyMonth)
            1 -> listOf(Month.EmptyMonth, list[0])
            2 -> list
            else -> listOf(list[list.lastIndex - 1], list.last())
        }
    }

    private fun updateIsError() {
        _uiState.update { it.copy(isError = true) }
    }

    private fun updateStatistics(statistics: List<Statistic>) {
        val translateScores = statistics.toMapScores { it.translateScore }
        val issueScores = statistics.toMapScores { it.issueScore }
        val dialogScores = statistics.toMapScores { it.dialogScore }
        val wordScores = statistics.toMapScores { it.wordScore }
        _uiState.update { state ->
            state.copy(
                translateScores = translateScores,
                issueScores = issueScores,
                dialogScores = dialogScores,
                wordScores = wordScores,
            )
        }
    }
}

/**
 * Holds StatisticScreen state
 *
 * @param months two last months
 * @param translateScores list scores of quest "Translate sentences"
 * @param issueScores list scores of quest "Tell about yourself"
 * @param dialogScores list scores of quest "Short dialogs"
 * @param wordScores list scores of quest "FlashCards"
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 */
internal data class StatisticUiState(
    val months: List<Month> = emptyList(),
    val translateScores: Map<Int, Int> = emptyMap(),
    val issueScores: Map<Int, Int> = emptyMap(),
    val dialogScores: Map<Int, Int> = emptyMap(),
    val wordScores: Map<Int, Int> = emptyMap(),
    val isError: Boolean = false,
)

fun List<Statistic>.toMapScores(mapper: (Statistic) -> Int): Map<Int, Int> {
    val mapResult = (1..31).associateWith { 0 }.toMutableMap()
    for (i in this) {
        mapResult[i.date.substring(0, 2).toInt()] = mapper(i)
    }
    return mapResult
}
