package com.viktoriagavrosh.englishsimulator.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.viktoriagavrosh.englishsimulator.data.DialogRepository
import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.GetQuestionsUseCase
import com.viktoriagavrosh.englishsimulator.data.IssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalDialogRepository
import com.viktoriagavrosh.englishsimulator.data.LocalGetQuestionsUseCase
import com.viktoriagavrosh.englishsimulator.data.LocalIssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalTranslateRepository
import com.viktoriagavrosh.englishsimulator.data.LocalWordRepository
import com.viktoriagavrosh.englishsimulator.data.PreferencesRepository
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.data.UserPreferencesRepository
import com.viktoriagavrosh.englishsimulator.data.WordRepository
import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.getDatabase
import com.viktoriagavrosh.englishsimulator.data.datastore.getDataStore
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordUpdateViewModel
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val TRANSLATE_REPOSITORY = "TranslateRepository"
const val ISSUE_REPOSITORY = "IssueRepository"
const val DIALOG_REPOSITORY = "DialogRepository"
const val WORD_REPOSITORY = "WordRepository"
const val TRANSLATE_USE_CASE = "TranslateUseCase"
const val ISSUE_USE_CASE = "IssueUseCase"
const val DIALOG_USE_CASE = "DialogUseCase"
const val WORD_USE_CASE = "WordUseCase"
const val TRANSLATE_SCREEN = "TranslateScreen"
const val ISSUE_SCREEN = "IssueScreen"
const val DIALOG_SCREEN = "DialogScreen"
const val WORD_SCREEN = "WordScreen"

/**
 * module with data sources for DI (Koin)
 */
val dbModule = module {
    single<AppDatabase> { getDatabase(get()) }
    single<DataStore<Preferences>> { getDataStore(get()) }
}

/**
 * module with all repositories for DI (Koin)
 */
val repositoriesModule = module {
    single<TranslateRepository> { LocalTranslateRepository(get()) }
    single<IssueRepository> { LocalIssueRepository(get()) }
    single<DialogRepository> { LocalDialogRepository(get()) }
    single<WordRepository> { LocalWordRepository(get()) }
    single<PreferencesRepository> { UserPreferencesRepository(get()) }
    single<GameRepository>(named(TRANSLATE_REPOSITORY)) { LocalTranslateRepository(get()) }
    single<GameRepository>(named(ISSUE_REPOSITORY)) { LocalIssueRepository(get()) }
    single<GameRepository>(named(DIALOG_REPOSITORY)) { LocalDialogRepository(get()) }
    single<GameRepository>(named(WORD_REPOSITORY)) { LocalWordRepository(get()) }
    single<GetQuestionsUseCase>(named(TRANSLATE_USE_CASE)) {
        LocalGetQuestionsUseCase(get(named(TRANSLATE_REPOSITORY)))
    }
    single<GetQuestionsUseCase>(named(ISSUE_USE_CASE)) {
        LocalGetQuestionsUseCase(get(named(ISSUE_REPOSITORY)))
    }
    single<GetQuestionsUseCase>(named(DIALOG_USE_CASE)) {
        LocalGetQuestionsUseCase(get(named(DIALOG_REPOSITORY)))
    }
    single<GetQuestionsUseCase>(named(WORD_USE_CASE)) {
        LocalGetQuestionsUseCase(get(named(WORD_REPOSITORY)))
    }
}

/**
 * module with all viewModels for DI (Koin)
 */
val viewModelsModule = module {
    viewModel(named(TRANSLATE_SCREEN)) {
        GameViewModel(useCase = get(qualifier = named(TRANSLATE_USE_CASE)), isToEnglish = get())
    }
    viewModel(named(ISSUE_SCREEN)) {
        GameViewModel(useCase = get(named(ISSUE_USE_CASE)), theme = get())
    }
    viewModel(named(DIALOG_SCREEN)) {
        GameViewModel(useCase = get(named(DIALOG_USE_CASE)))
    }
    viewModel(named(WORD_SCREEN)) {
        GameViewModel(useCase = get(named(WORD_USE_CASE)), theme = get(), isToEnglish = get())
    }
    viewModel { IssueMenuViewModel(get()) }
    viewModel { WordMenuViewModel(get()) }
    viewModel { WordUpdateViewModel(get(), get()) }
}
