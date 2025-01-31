package com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model

import com.viktoriagavrosh.englishsimulator.model.QuizName
import com.viktoriagavrosh.englishsimulator.model.UiItem

/**
 * Converts [UiItem] instance to [GameQuestion] instance for ui (GameScreen)
 *
 * @return [GameQuestion] instance
 */
fun UiItem.toGameQuestion(isToEnglish: Boolean = false): GameQuestion {
    val question = if (isToEnglish) answerText else questionText
    val translate = if (isToEnglish) questionText else answerText

    return GameQuestion(
        id = id,
        question = question,
        translate = translate,
    )
}

/**
 * Converts [GameQuestion] instance to [UiItem] instance for ui (GameScreen)
 *
 * @param theme theme of issue
 * @return [UiItem] instance
 */
fun GameQuestion.toUiItem(
    theme: String = "",
    isToEnglish: Boolean = true,
    quizName: QuizName = QuizName.Word
): UiItem {
    val ruText = if (isToEnglish) question else translate
    val enText = if (isToEnglish) translate else question

    return UiItem(
        id = id,
        questionText = enText,
        answerText = ruText,
        theme = theme,
        quizName = quizName
    )
}
