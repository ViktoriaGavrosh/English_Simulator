package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.data.database.AppDatabase
import com.viktoriagavrosh.englishsimulator.data.database.getDatabase
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueGameViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuViewModel
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule  = module {
    single<AppDatabase> { getDatabase( get() ) }
    single<TranslateRepository> { LocalTranslateRepository( get() ) }
    single<IssueRepository> { LocalIssueRepository( get() ) }
    viewModel { TranslateViewModel( get() ) }
    viewModel { IssueGameViewModel( get() ) }
    viewModel { IssueMenuViewModel( get() ) }
}
