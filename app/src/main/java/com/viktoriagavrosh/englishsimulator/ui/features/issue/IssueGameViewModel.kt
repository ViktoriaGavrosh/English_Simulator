package com.viktoriagavrosh.englishsimulator.ui.features.issue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.IssueRepository
import com.viktoriagavrosh.englishsimulator.ui.features.translate.UiState
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
 * @param issueRepository instance of [IssueRepository]
 * @param theme describes what action will be shown by Ui
 */
class IssueGameViewModel(
    private val issueRepository: IssueRepository,
    private val theme: String,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    private lateinit var gameQuestions: List<GameQuestion>

    init {
        initUiState()
    }

    internal val uiState: StateFlow<GameUiState>
        get() = _uiState.asStateFlow()

    /**
     * Update gameQuestion and score value of [GameUiState]
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
     * Update [UiState] with data from [IssueRepository]
     */
    internal fun initUiState() {
        val requestResultFlow = if (theme.isNotEmpty()) {
            issueRepository.getAllIssueByTheme(theme = theme)
        } else {
            issueRepository.getAllIssue()
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
                        it.toGameQuestion()
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
 * Holds IssueGameScreen state
 *
 * @param gameQuestion instance [GameQuestion]
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 * @param score quest score
 */
internal data class GameUiState(
    val gameQuestion: GameQuestion = GameQuestion(),
    val isError: Boolean = false,
    val score: Int = 0,
)
