package com.viktoriagavrosh.englishsimulator.utils

import com.viktoriagavrosh.englishsimulator.model.DialogDb
import com.viktoriagavrosh.englishsimulator.model.IssueDb
import com.viktoriagavrosh.englishsimulator.model.QuizName
import com.viktoriagavrosh.englishsimulator.model.SentenceDb
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.model.WordDb

/**
 * Converts [SentenceDb] instance to [UiItem] instance for repository
 *
 * @return [UiItem] instance
 */
internal fun SentenceDb.toUiItem(): UiItem {
    return UiItem(
        id = id,
        questionText = enText,
        answerText = ruText,
        quizName = QuizName.Translate
    )
}

/**
 * Converts [IssueDb] instance to [UiItem] instance for repository
 *
 * @return [UiItem] instance
 */
internal fun IssueDb.toUiItem(): UiItem {
    return UiItem(
        id = id,
        questionText = englishQuestion,
        answerText = russianQuestion,
        theme = theme,
        quizName = QuizName.Issue
    )
}

/**
 * Converts [DialogDb] instance to [UiItem] instance for repository
 *
 * @return [UiItem] instance
 */
internal fun DialogDb.toUiItem(): UiItem {
    return UiItem(
        id = id,
        questionText = question,
        answerText = shortAnswer,
        quizName = QuizName.Dialog
    )
}

/**
 * Converts [WordDb] instance to [UiItem] instance for repository
 *
 * @return [UiItem] instance
 */
internal fun WordDb.toUiItem(): UiItem {
    return UiItem(
        id = id,
        questionText = englishWord,
        answerText = russianWord,
        theme = theme,
        quizName = QuizName.Word
    )
}

/**
 * Converts [UiItem] instance to [WordDb] instance for repository
 *
 * @return [WordDb] instance
 */
internal fun UiItem.toWordDb(): WordDb {
    if (this.quizName != QuizName.Word) {
        throw ClassCastException("You can't convert this type to WordDb")
    }
    return WordDb(
        id = id,
        englishWord = questionText,
        russianWord = answerText,
        theme = theme,
    )
}
