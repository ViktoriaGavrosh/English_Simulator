package com.viktoriagavrosh.englishsimulator.utils

import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.model.SentenceDb

/**
 * Converts [SentenceDb] instance to [Sentence] instance for ui
 *
 * @return [Sentence] instance
 */
internal fun SentenceDb.toSentence(): Sentence {
    return Sentence(
        id = id,
        ruText = ruText,
        enText = enText,
    )
}
