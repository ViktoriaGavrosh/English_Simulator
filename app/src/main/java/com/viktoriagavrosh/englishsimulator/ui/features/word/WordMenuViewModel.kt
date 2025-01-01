package com.viktoriagavrosh.englishsimulator.ui.features.word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.WordRepository
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update item from repository data source
 *
 * @param repository instance of [WordRepository]
 */
class WordMenuViewModel(
    private val repository: WordRepository,
) : ViewModel() {

    val uiState = repository.getAllThemes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = RequestResult.Loading()
    )

    private var _selectedOption = MutableStateFlow(Quest.RuToEn)
    internal val selectedOption: StateFlow<Quest>
        get() = _selectedOption.asStateFlow()

    fun updateLanguage(newValue: String) {
        val quest = when (newValue) {
            Quest.RuToEn.text -> Quest.RuToEn
            Quest.EnToRu.text -> Quest.EnToRu
            else -> throw IllegalArgumentException("Element of dropdownMenu does not add to Quest enum")
        }
        viewModelScope.launch {
            _selectedOption.update { quest }
        }
    }
}
