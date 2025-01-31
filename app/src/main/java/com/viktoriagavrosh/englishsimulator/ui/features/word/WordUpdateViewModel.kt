package com.viktoriagavrosh.englishsimulator.ui.features.word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.WordRepository
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from repository data source
 *
 * @param repository instance of [WordRepository]
 * @param wordId describes what action will be shown by Ui
 */
class WordUpdateViewModel(
    private val repository: WordRepository,
    private val wordId: Int,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UpdateUiState())
    val uiState: StateFlow<UpdateUiState>
        get() = _uiState

    init {
        initUiState(wordId = wordId)
    }

    /**
     * Update english text value of [UpdateUiState]
     *
     * @param text new value
     */
    fun updateEnglishText(text: String) {
        viewModelScope.launch {
            val newWord = uiState.first().word.copy(
                questionText = text
            )
            updateState(newWord)
        }
    }

    /**
     * Update russian text value of [UpdateUiState]
     *
     * @param text new value
     */
    fun updateRussianText(text: String) {
        viewModelScope.launch {
            val newWord = uiState.first().word.copy(
                answerText = text
            )
            updateState(newWord)
        }
    }

    /**
     * Update theme value of [UpdateUiState]
     *
     * @param text new value
     */
    fun updateTheme(text: String) {
        viewModelScope.launch {
            val newWord = uiState.first().word.copy(
                theme = text
            )
            updateState(newWord)
        }
    }

    /**
     * Save new word into datasource
     *
     */
    fun saveWord() {
        viewModelScope.launch {
            val newWord = uiState.first().word
            if (newWord.id == 0) {
                repository.insertWord(word = newWord)
            } else {
                repository.updateWord(word = newWord)
            }
        }
    }

    /**
     * Delete word from datasource
     *
     */
    fun deleteWord() {
        viewModelScope.launch {
            val newWord = uiState.first().word
            repository.deleteWord(word = newWord)
        }
    }

    fun initUiState(wordId: Int = 0) {               // not private for testing
        viewModelScope.launch {
            if (wordId == 0) return@launch
            val flowResult = repository.getWordById(id = wordId).first()

            if (flowResult is RequestResult.Success) {
                _uiState.update {
                    val word = flowResult.data
                    it.copy(
                        word = word,
                        isWordValid = validateWord(word = word),
                    )
                }
            } else {
                _uiState.update {
                    it.copy(word = UiItem())
                }
            }
        }
    }

    private fun updateState(word: UiItem) {
        val isWordValid = validateWord(word)
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    word = word,
                    isWordValid = isWordValid
                )
            }
        }
    }


    private fun validateWord(word: UiItem): Boolean {
        return word.questionText.isNotEmpty()
                && word.answerText.isNotEmpty()
                && word.theme.isNotEmpty()
    }
}

/**
 * holds [WordUpdateScreen] state
 *
 * @param word instance [UiItem]
 * @param isWordValid if true, new word can be saved
 */
data class UpdateUiState(
    val word: UiItem = UiItem(),
    val isWordValid: Boolean = false,
)
