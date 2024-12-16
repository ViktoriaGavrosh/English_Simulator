package com.viktoriagavrosh.englishsimulator.di

import com.viktoriagavrosh.englishsimulator.data.IssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalIssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalTranslateRepository
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.getDatabase
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueGameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateGameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * module for DI (Koin)
 */
val appModule = module {
    single<AppDatabase> { getDatabase(get()) }
    single<TranslateRepository> { LocalTranslateRepository(get()) }
    single<IssueRepository> { LocalIssueRepository(get()) }
    viewModel { TranslateGameViewModel(get(), get()) }
    viewModel { IssueGameViewModel(get(), get()) }
    viewModel { IssueMenuViewModel(get()) }
}
