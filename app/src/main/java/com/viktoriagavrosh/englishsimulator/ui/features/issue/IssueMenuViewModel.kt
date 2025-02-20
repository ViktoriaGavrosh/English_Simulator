package com.viktoriagavrosh.englishsimulator.ui.features.issue

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktoriagavrosh.englishsimulator.data.IssueRepository
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve and update item from repository data source
 *
 * @param repository instance of [IssueRepository]
 */
class IssueMenuViewModel(
    private val repository: IssueRepository,
) : ViewModel() {

    val uiState = repository.getAllThemes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = RequestResult.Loading()
    ).also {
        Log.e("123", "VM")      // TODO log
    }
}
