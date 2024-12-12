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
