package com.viktoriagavrosh.englishsimulator.ui.screens.repeat

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.englishsimulator.SimulatorApplication
import com.viktoriagavrosh.englishsimulator.data.RepeatRepository
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepeatViewModel(
    private val repeatRepository: RepeatRepository,
) : ViewModel() {

    private lateinit var _uiState: MutableStateFlow<UiState>

    init {
        initUiState()
    }

    internal val uiState: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    internal var count = mutableIntStateOf(0)
        private set

    internal fun increaseCount() {
        count.intValue = count.intValue++
    }

    internal fun updateSentence() {
        val newSentence = _uiState.value.sentences.random()
        _uiState.update {
            it.copy(
                sentence = newSentence,
            )
        }
    }

    private fun initUiState() {
        val requestResultFlow = repeatRepository.getAllSentences()

        viewModelScope.launch {
            val result = requestResultFlow.first()
            if (result is RequestResult.Error) {
                _uiState.update {
                    it.copy(
                        isError = true,
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        sentences = result.data ?: emptyList(),
                        sentence = result.data?.random() ?: Sentence(),
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SimulatorApplication)
                val repeatRepository = application.container.repeatRepository
                RepeatViewModel(repeatRepository = repeatRepository)
            }
        }
    }
}

internal data class UiState(
    val sentences: List<Sentence> = emptyList(),
    val sentence: Sentence = Sentence(),
    val isError: Boolean,
)
