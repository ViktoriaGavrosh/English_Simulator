package com.viktoriagavrosh.englishsimulator.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.viktoriagavrosh.englishsimulator.data.DialogRepository
import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.IssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalDialogRepository
import com.viktoriagavrosh.englishsimulator.data.LocalIssueRepository
import com.viktoriagavrosh.englishsimulator.data.LocalQuestionManager
import com.viktoriagavrosh.englishsimulator.data.LocalStatisticRepository
import com.viktoriagavrosh.englishsimulator.data.LocalTranslateRepository
import com.viktoriagavrosh.englishsimulator.data.LocalWordRepository
import com.viktoriagavrosh.englishsimulator.data.QuestionManager
import com.viktoriagavrosh.englishsimulator.data.StatisticRepository
import com.viktoriagavrosh.englishsimulator.data.TranslateRepository
import com.viktoriagavrosh.englishsimulator.data.WordRepository
import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.getDatabase
import com.viktoriagavrosh.englishsimulator.data.datastore.PreferencesManager
import com.viktoriagavrosh.englishsimulator.data.datastore.UserPreferencesManager
import com.viktoriagavrosh.englishsimulator.data.datastore.getDataStore
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.StatisticViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordUpdateViewModel
import com.viktoriagavrosh.englishsimulator.ui.navigation.UpdateStatisticViewModel
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val TRANSLATE_REPOSITORY = "TranslateRepository"
const val ISSUE_REPOSITORY = "IssueRepository"
const val DIALOG_REPOSITORY = "DialogRepository"
const val WORD_REPOSITORY = "WordRepository"
const val TRANSLATE_QUESTION_MANAGER = "TranslateQuestionManager"
const val ISSUE_QUESTION_MANAGER = "IssueQuestionManager"
const val DIALOG_QUESTION_MANAGER = "DialogQuestionManager"
const val WORD_QUESTION_MANAGER = "WordQuestionManager"
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
    single<PreferencesManager> { UserPreferencesManager(get()) }
    single<StatisticRepository> { LocalStatisticRepository(get(), get()) }

    single<GameRepository>(named(TRANSLATE_REPOSITORY)) { LocalTranslateRepository(get()) }
    single<GameRepository>(named(ISSUE_REPOSITORY)) { LocalIssueRepository(get()) }
    single<GameRepository>(named(DIALOG_REPOSITORY)) { LocalDialogRepository(get()) }
    single<GameRepository>(named(WORD_REPOSITORY)) { LocalWordRepository(get()) }
    single<QuestionManager>(named(TRANSLATE_QUESTION_MANAGER)) {
        LocalQuestionManager(get(named(TRANSLATE_REPOSITORY)))
    }
    single<QuestionManager>(named(ISSUE_QUESTION_MANAGER)) {
        LocalQuestionManager(get(named(ISSUE_REPOSITORY)))
    }
    single<QuestionManager>(named(DIALOG_QUESTION_MANAGER)) {
        LocalQuestionManager(get(named(DIALOG_REPOSITORY)))
    }
    single<QuestionManager>(named(WORD_QUESTION_MANAGER)) {
        LocalQuestionManager(get(named(WORD_REPOSITORY)))
    }
}

/**
 * module with all viewModels for DI (Koin)
 */
val viewModelsModule = module {
    viewModel(named(TRANSLATE_SCREEN)) {
        GameViewModel(
            questionManager = get(qualifier = named(TRANSLATE_QUESTION_MANAGER)),
            isToEnglish = get()
        )
    }
    viewModel(named(ISSUE_SCREEN)) {
        GameViewModel(questionManager = get(named(ISSUE_QUESTION_MANAGER)), theme = get())
    }
    viewModel(named(DIALOG_SCREEN)) {
        GameViewModel(questionManager = get(named(DIALOG_QUESTION_MANAGER)))
    }
    viewModel(named(WORD_SCREEN)) {
        GameViewModel(
            questionManager = get(named(WORD_QUESTION_MANAGER)),
            theme = get(),
            isToEnglish = get()
        )
    }
    viewModel { IssueMenuViewModel(get()) }
    viewModel { WordMenuViewModel(get()) }
    viewModel { WordUpdateViewModel(get(), get()) }
    viewModel { UpdateStatisticViewModel(get(), get()) }
    viewModel { StatisticViewModel(get()) }
}
