package com.viktoriagavrosh.englishsimulator.utils

import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.model.SentenceDb

internal fun SentenceDb.toSentence(): Sentence {
    return Sentence(
        id = id,
        ruText = ruText,
        enText = enText,
    )
}
