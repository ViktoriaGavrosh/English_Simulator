package com.viktoriagavrosh.englishsimulator.ui.screens.repeat

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

    private val _uiState = MutableStateFlow(UiState())
    private lateinit var sentences: List<Sentence>

    init {
        initUiState()
    }

    internal val uiState: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    internal val count = MutableStateFlow(0)

    internal fun updateSentence() {
        _uiState.update {
            it.copy(
                sentence = sentences.random(),
            )
        }
        increaseCount()
    }

    internal fun initUiState() {
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
                sentences = result.data?.shuffled() ?: emptyList()
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

    private fun increaseCount() {
        val newCount = count.value + 1
        count.value = newCount
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
    val sentence: Sentence = Sentence(),
    val isError: Boolean = false,
)
