package com.viktoriagavrosh.englishsimulator.ui.features.word

import androidx.lifecycle.ViewModel
import com.viktoriagavrosh.englishsimulator.data.WordRepository

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

}
