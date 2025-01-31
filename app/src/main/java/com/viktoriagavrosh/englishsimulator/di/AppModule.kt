package com.viktoriagavrosh.englishsimulator.di

import com.viktoriagavrosh.englishsimulator.data.DialogRepository
import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.IssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalDialogRepository
import com.viktoriagavrosh.englishsimulator.data.LocalIssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalTranslateRepository
import com.viktoriagavrosh.englishsimulator.data.LocalWordRepository
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.data.WordRepository
import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.getDatabase
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.GameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordUpdateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
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
    single<GameRepository>(named("TranslateRepository")) { LocalTranslateRepository(get()) }
    single<GameRepository>(named("IssueRepository")) { LocalIssueRepository(get()) }
    single<GameRepository>(named("DialogRepository")) { LocalDialogRepository(get()) }
    single<GameRepository>(named("WordRepository")) { LocalWordRepository(get()) }
    viewModel(named("TranslateScreen")) {
        GameViewModel(
            repository = get(qualifier = named("TranslateRepository")),
            isToEnglish = get()
        )
    }
    viewModel(named("IssueScreen")) {
        GameViewModel(
            repository = get(named("IssueRepository")),
            theme = get(),
        )
    }
    viewModel(named("DialogScreen")) {
        GameViewModel(repository = get(named("DialogRepository")))
    }
    viewModel(named("WordScreen")) {
        GameViewModel(
            repository = get(named("WordRepository")),
            theme = get(),
            isToEnglish = get()
        )
    }
    viewModel { IssueMenuViewModel(get()) }
    viewModel { WordMenuViewModel(get()) }
    viewModel { WordUpdateViewModel(get(), get()) }
}
