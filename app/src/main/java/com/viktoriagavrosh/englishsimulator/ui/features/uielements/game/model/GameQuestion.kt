package com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.model

import com.viktoriagavrosh.englishsimulator.model.Issue
import com.viktoriagavrosh.englishsimulator.model.Sentence

/**
 * Model represents a single item for ui (GameScreen)
 *
 * @param id unique object identifier
 * @param question text in English
 * @param translate text in Russian
 */
data class GameQuestion(
    val id: Int = 0,
    val question: String = "",
    val translate: String = "",
)

/**
 * Converts [Sentence] instance to [GameQuestion] instance for ui (GameScreen)
 *
 * @param isToEnglish if true - quiz "Translate from Russian to English"
 * @return [GameQuestion] instance
 */
fun Sentence.toGameQuestion(isToEnglish: Boolean = true): GameQuestion {
    val question = if (isToEnglish) ruText else enText
    val translate = if (isToEnglish) enText else ruText

    return GameQuestion(
        id = id,
        question = question,
        translate = translate,
    )
}

/**
 * Converts [Issue] instance to [GameQuestion] instance for ui (GameScreen)
 *
 * @return [GameQuestion] instance
 */
fun Issue.toGameQuestion(): GameQuestion {
    return GameQuestion(
        id = id,
        question = englishQuestion,
        translate = russianQuestion,
    )
}

/**
 * Converts [GameQuestion] instance to [Sentence] instance for ui (GameScreen)
 *
 * @param isToEnglish if true - quiz "Translate from Russian to English"
 * @return [Sentence] instance
 */
fun GameQuestion.toSentence(isToEnglish: Boolean): Sentence {
    val ruText = if (isToEnglish) question else translate
    val enText = if (isToEnglish) translate else question

    return Sentence(
        id = id,
        ruText = ruText,
        enText = enText,
    )
}

/**
 * Converts [GameQuestion] instance to [Issue] instance for ui (GameScreen)
 *
 * @param theme theme of issue
 * @return [Issue] instance
 */
fun GameQuestion.toIssue(theme: String): Issue {
    return Issue(
        id = id,
        englishQuestion = question,
        russianQuestion = translate,
        theme = theme,
    )
}

