package com.viktoriagavrosh.englishsimulator.di

import com.viktoriagavrosh.englishsimulator.data.DialogRepository
import com.viktoriagavrosh.englishsimulator.data.IssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalDialogRepository
import com.viktoriagavrosh.englishsimulator.data.LocalIssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalTranslateRepository
import com.viktoriagavrosh.englishsimulator.data.LocalWordRepository
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.data.WordRepository
import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.getDatabase
import com.viktoriagavrosh.englishsimulator.ui.features.dialog.DialogGameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueGameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateGameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordGameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordUpdateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * module for DI (Koin)
 */
val appModule = module {
    single<AppDatabase> { getDatabase(get()) }
    single<TranslateRepository> { LocalTranslateRepository(get()) }
    single<IssueRepository> { LocalIssueRepository(get()) }
    single<DialogRepository> { LocalDialogRepository(get()) }
    single<WordRepository> { LocalWordRepository(get()) }
    viewModel { TranslateGameViewModel(get(), get()) }
    viewModel { IssueGameViewModel(get(), get()) }
    viewModel { IssueMenuViewModel(get()) }
    viewModel { DialogGameViewModel(get()) }
    viewModel { WordMenuViewModel(get()) }
    viewModel { WordGameViewModel(get(), get(), get()) }
    viewModel { WordUpdateViewModel(get(), get()) }
}
