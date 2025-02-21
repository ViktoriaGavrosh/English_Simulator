package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.model.GameQuestionUi
import com.viktoriagavrosh.englishsimulator.model.dbmodel.DialogDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.IssueDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.SentenceDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.StatisticDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.WordDb
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.model.MenuButtonItem

internal object FakeSource {
    val fakeSentencesDb = List(5) {
        val i = it + 1
        SentenceDb(
            id = i,
            ruText = "ru $i",
            enText = "en $i",
        )
    }

    val fakeIssuesDb = List(5) {
        val i = it + 1
        IssueDb(
            id = i,
            englishQuestion = "en $i",
            russianQuestion = "ru $i",
            theme = if (i % 2 == 0) "Theme 1" else "Theme 2",
        )
    }

    val fakeDialogsDb = List(5) {
        val i = it + 1
        DialogDb(
            id = i,
            question = "question $i",
            shortAnswer = "answer $i",
        )
    }

    val fakeWordsDb = List(5) {
        val i = it + 1
        WordDb(
            id = i,
            englishWord = "english word $i",
            russianWord = "russian word $i",
            theme = if (i % 2 == 0) "Theme 1" else "Theme 2",
        )
    }

    val fakeStatisticDb = List(5) {
        val i = it + 1
        StatisticDb(
            id = i,
            date = "14-0$i-2024",
            translateScore = i,
            issueScore = i,
            dialogScore = i,
            wordScore = i,
        )
    }

    val fakeButtonItems = List(5) {
        MenuButtonItem(title = "Title $it")
    }

    val fakeGameQuestion = List(5) {
        GameQuestionUi(
            id = it,
            question = "question $it",
            translate = "translate $it",
        )
    }
}
