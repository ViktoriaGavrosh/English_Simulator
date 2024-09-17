package com.viktoriagavrosh.englishsimulator.fake

import com.viktoriagavrosh.englishsimulator.model.SentenceDb

object FakeSource {
    val fakeSentencesDb = List(5) {
        val i = it + 1
        SentenceDb(
            id = i,
            ruText = "ru $i",
            enText = "en $i",
        )
    }

    val fakeSentencesUi = List(5) {
        SentenceDb(
            id = it,
            ruText = "ru $it",
            enText = "en $it",
        )
    }
}
