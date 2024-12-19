package com.viktoriagavrosh.englishsimulator.ui.features.dialog

import androidx.lifecycle.ViewModel
import com.viktoriagavrosh.englishsimulator.data.DialogRepository

/**
 * ViewModel to retrieve and update item from repository data source
 *
 * @param dialogRepository instance of [DialogRepository]
 */
class DialogGameViewModel (
    private val dialogRepository: DialogRepository,
) : ViewModel()
