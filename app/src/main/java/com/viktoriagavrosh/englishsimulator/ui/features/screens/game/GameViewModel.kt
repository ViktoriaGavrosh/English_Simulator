package com.viktoriagavrosh.englishsimulator.ui.features.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.GameQuestion
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.toGameQuestion
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
 * @param repository instance of [GameRepository]
 * @param theme describes what action will be shown by Ui
 * @param isToEnglish describes what action will be shown by Ui
 */
class GameViewModel(
    private val repository: GameRepository,
    private val theme: String = "",
    private val isToEnglish: Boolean = false,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    private lateinit var gameQuestions: List<GameQuestion>

    init {
        initUiState()
    }

    internal val uiState: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    /**
     * Update gameQuestion and score value of [UiState]
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
     * Update [UiState] with data from [GameRepository]
     */
    private fun initUiState() {
        val requestResultFlow = if (theme.isNotEmpty()) {
            repository.getAllItemsByTheme(theme = theme)
        } else {
            repository.getAllItems()
        }

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
 * Holds GameScreen state
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
