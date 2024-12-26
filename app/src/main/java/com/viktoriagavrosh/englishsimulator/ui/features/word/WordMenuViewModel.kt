package com.viktoriagavrosh.englishsimulator.ui.features.word

import androidx.lifecycle.ViewModel
import com.viktoriagavrosh.englishsimulator.data.WordRepository

/**
 * ViewModel to retrieve and update item from repository data source
 *
 * @param repository instance of [WordRepository]
 */
class WordMenuViewModel(
    private val repository: WordRepository,
) : ViewModel() {

}
