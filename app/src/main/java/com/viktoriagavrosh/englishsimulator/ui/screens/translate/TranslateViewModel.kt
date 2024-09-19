package com.viktoriagavrosh.englishsimulator.ui.screens.translate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.englishsimulator.SimulatorApplication
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.model.Sentence
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
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    private lateinit var sentences: List<Sentence>

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
                sentence = sentences.random(),
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
                sentences = result.data?.shuffled()
                    ?.filterIndexed { index, _ -> index < 40 }
                    ?: emptyList()
                if (sentences.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            sentence = sentences.random(),
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

    /**
     * Contain factory to create [TranslateViewModel] instance
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SimulatorApplication)
                val repeatRepository = application.container.repeatRepository
                TranslateViewModel(translateRepository = repeatRepository)
            }
        }
    }
}

/**
 * Holds TranslateScreen state
 *
 * @param sentence instance [Sentence]
 * @param isError boolean parameter describes screen state. If true ErrorScreen will be shown.
 * @param score quest score
 */
internal data class UiState(
    val sentence: Sentence = Sentence(),
    val isError: Boolean = false,
    val score: Int = 0,
)
