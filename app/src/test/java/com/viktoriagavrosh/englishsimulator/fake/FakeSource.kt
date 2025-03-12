package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.model.Goal
import com.viktoriagavrosh.englishsimulator.model.dbmodel.DialogDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.IssueDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.SentenceDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.StatisticDb
import com.viktoriagavrosh.englishsimulator.model.dbmodel.WordDb


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

    val fakeWordsDb = List(5) {
        WordDb(
            id = it,
            englishWord = "english word $it",
            russianWord = "russian word $it",
            theme = if (it % 2 == 0) "Theme 1" else "Theme 2",
        )
    }

    val fakeStatisticsDb = List(5) {
        val i = it + 1
        StatisticDb(
            id = i,
            date = "01-0$i-2000",
            translateScore = i,
            issueScore = i,
            dialogScore = i,
            wordScore = i,
        )
    }

    val fakeGoal = Goal(
        translateGoal = 2,
        issueGoal = 3,
        dialogGoal = 4,
        wordGoal = 5
    )
}
