package com.viktoriagavrosh.englishsimulator.utils

import com.viktoriagavrosh.englishsimulator.model.Issue
import com.viktoriagavrosh.englishsimulator.model.IssueDb
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.model.SentenceDb

/**
 * Converts [SentenceDb] instance to [Sentence] instance for repository
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

/**
 * Converts [IssueDb] instance to [Issue] instance for repository
 *
 * @return [Issue] instance
 */
internal fun IssueDb.toIssue(): Issue {
    return Issue(
        id = id,
        englishQuestion = englishQuestion,
        russianQuestion = russianQuestion,
        theme = theme,
    )
}
