package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.model.SentenceDb

object FakeSource {
    val fakeSentencesDb = List(5) {
        SentenceDb(
            id = it,
            ruText = "ru $it",
            enText = "en $it",
        )
    }

    val fakeSentences = List(5) {
        Sentence(
            id = it,
            ruText = "ru $it",
            enText = "en $it",
        )
    }
}
