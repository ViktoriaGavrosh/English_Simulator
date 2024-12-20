package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.model.DialogDb
import com.viktoriagavrosh.englishsimulator.model.IssueDb
import com.viktoriagavrosh.englishsimulator.model.SentenceDb


internal object FakeSource {
    val fakeSentencesDb = List(5) {
        SentenceDb(
            id = it,
            ruText = "ru $it",
            enText = "en $it",
        )
    }

    val fakeIssuesDb = List(5) {
        IssueDb(
            id = it,
            englishQuestion = "english $it",
            russianQuestion = "russian $it",
            theme = if (it % 2 == 0) "Theme 1" else "Theme 2",
        )
    }

    val fakeDialogsDb = List(5) {
        DialogDb(
            id = it,
            question = "question $it",
            shortAnswer = "answer $it",
        )
    }
}
