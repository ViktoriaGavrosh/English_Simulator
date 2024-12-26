package com.viktoriagavrosh.englishsimulator.utils

import com.viktoriagavrosh.englishsimulator.model.Dialog
import com.viktoriagavrosh.englishsimulator.model.DialogDb
import com.viktoriagavrosh.englishsimulator.model.Issue
import com.viktoriagavrosh.englishsimulator.model.IssueDb
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.model.SentenceDb
import com.viktoriagavrosh.englishsimulator.model.Word
import com.viktoriagavrosh.englishsimulator.model.WordDb

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

/**
 * Converts [DialogDb] instance to [Dialog] instance for repository
 *
 * @return [Dialog] instance
 */
internal fun DialogDb.toDialog(): Dialog {
    return Dialog(
        id = id,
        question = question,
        shortAnswer = shortAnswer,
    )
}

/**
 * Converts [WordDb] instance to [Word] instance for repository
 *
 * @return [Word] instance
 */
internal fun WordDb.toWord(): Word {
    return Word(
        id = id,
        englishWord = englishWord,
        russianWord = russianWord,
        theme = theme,
    )
}

/**
 * Converts [Word] instance to [WordDb] instance for repository
 *
 * @return [WordDb] instance
 */
internal fun Word.toWordDb(): WordDb {
    return WordDb(
        id = id,
        englishWord = englishWord,
        russianWord = russianWord,
        theme = theme,
    )
}
