package com.viktoriagavrosh.englishsimulator.ui.features.translate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.model.GameQuestion
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.model.toGameQuestion
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
 * @param translateRepository instance of [TranslateRepository]
 */
class TranslateViewModel(
    private val translateRepository: TranslateRepository,
    private val isToEnglish: Boolean,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    private lateinit var gameQuestions: List<GameQuestion>

    init {
        initUiState()
    }

    internal val uiState: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    /**
     * Update sentence and score value of [UiState]
     */
    internal fun updateUiState() {
        _uiState.update {
            it.copy(
                gameQuestion = gameQuestions.random(),
            )
        }
        increaseScore()
    }

    /**
     * Update [UiState] with data from [TranslateRepository]
     */
    internal fun initUiState() {
        val requestResultFlow = translateRepository.getAllSentences()

        viewModelScope.launch {
            val result = requestResultFlow.first()
            if (result is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true,
                    )
                }
            } else {
                gameQuestions = result.data
                    ?.map {
                        it.toGameQuestion(isToEnglish)
                    }
                    ?: emptyList()
                if (gameQuestions.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            gameQuestion = gameQuestions.random(),
                        )
                    }
                }
            }
        }
    }

    /**
     * Update score value of [UiState]
     */
    private fun increaseScore() {
        viewModelScope.launch {
            val newCount = uiState.first().score + 1
            _uiState.update {
                it.copy(
                    score = newCount
                )
            }
        }
    }
}

/**
 * Holds TranslateGameScreen state
 *
 * @param gameQuestion instance [GameQuestion]
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 * @param score quest score
 */
internal data class UiState(
    val gameQuestion: GameQuestion = GameQuestion(),
    val isError: Boolean = false,
    val score: Int = 0,
)
