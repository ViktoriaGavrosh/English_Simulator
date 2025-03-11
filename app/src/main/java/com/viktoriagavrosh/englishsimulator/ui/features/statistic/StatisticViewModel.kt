package com.viktoriagavrosh.englishsimulator.ui.features.statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.StatisticRepository
import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.model.Goal
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


/**
 * ViewModel to retrieve and update item from repository data source
 *
 * @param repository instance of [StatisticRepository]
 */
class StatisticViewModel(
    private val repository: StatisticRepository,
    manager: PreferencesManager,
) : ViewModel() {
    private val _uiState = MutableStateFlow(StatisticUiState())
    private var months: List<Month> = emptyList()

    val goalState = try {
        manager.getGoal()
    } catch (e: Exception) {
        flow { emit(Goal()) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = Goal()
    )

    init {
        initUiState()
    }

    internal val uiState: StateFlow<StatisticUiState>
        get() = _uiState.asStateFlow()

    fun updateUiState(monthIndex: Int) {
        viewModelScope.launch {
            val month = months[monthIndex]
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

    fun getListMonths(): List<Month> = months

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
                months = prepareMonths(requestResult.data)
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
        val translateScores = statistics.toListScores { it.translateScore }
        val issueScores = statistics.toListScores { it.issueScore }
        val dialogScores = statistics.toListScores { it.dialogScore }
        val wordScores = statistics.toListScores { it.wordScore }
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
 * @param translateScores list scores of quest "Translate sentences"
 * @param issueScores list scores of quest "Tell about yourself"
 * @param dialogScores list scores of quest "Short dialogs"
 * @param wordScores list scores of quest "FlashCards"
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 */
internal data class StatisticUiState(
    val translateScores: List<Int> = emptyList(),
    val issueScores: List<Int> = emptyList(),
    val dialogScores: List<Int> = emptyList(),
    val wordScores: List<Int> = emptyList(),
    val isError: Boolean = false,
)

fun List<Statistic>.toListScores(mapper: (Statistic) -> Int): List<Int> {
    val listResult = MutableList(31) { 0 }
    for (i in this) {
        val day = i.date.take(2).toInt()
        listResult[day - 1] = mapper(i)
    }
    return listResult
}
