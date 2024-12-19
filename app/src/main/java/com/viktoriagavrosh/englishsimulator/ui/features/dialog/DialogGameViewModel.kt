package com.viktoriagavrosh.englishsimulator.ui.features.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.DialogRepository
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.GameQuestion
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.toGameQuestion
import com.viktoriagavrosh.englishsimulator.ui.features.translate.UiState
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
 * @param dialogRepository instance of [DialogRepository]
 */
class DialogGameViewModel(
    private val dialogRepository: DialogRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    private lateinit var gameQuestions: List<GameQuestion>

    init {
        initUiState()
    }

    internal val uiState: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    /**
     * Update dialog and score value of [UiState]
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
     * Update [UiState] with data from [DialogRepository]
     */
    private fun initUiState() {
        val requestResultFlow = dialogRepository.getAllDialogs()

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
