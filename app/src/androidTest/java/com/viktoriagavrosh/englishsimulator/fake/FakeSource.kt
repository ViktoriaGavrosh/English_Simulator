package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.model.IssueDb
import com.viktoriagavrosh.englishsimulator.model.SentenceDb
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.GameQuestion
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.model.MenuButtonItem

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
            theme = if (i % 2 == 0) "Theme 1" else "Theme 2"
        )
    }

    val fakeButtonItems = List(5) {
        MenuButtonItem(title = "Title $it")
    }

    val fakeGameQuestion = List(5) {
        GameQuestion(
            id = it,
            question = "question $it",
            translate = "translate $it",
        )
    }
}
