package com.viktoriagavrosh.englishsimulator.ui.features.word

import androidx.lifecycle.ViewModel
import com.viktoriagavrosh.englishsimulator.data.WordRepository

/**
 * ViewModel to retrieve and update item from repository data source
 *
 * @param repository instance of [WordRepository]
 * @param theme describes what action will be shown by Ui
 * @param isToEnglish if true quiz "Translate from Russian to English"
 */
class WordGameViewModel(
    private val repository: WordRepository,
    private val theme: String,
    private val isToEnglish: Boolean,
) : ViewModel() {

}
