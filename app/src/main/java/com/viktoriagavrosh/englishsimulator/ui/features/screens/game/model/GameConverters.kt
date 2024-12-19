package com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model

import com.viktoriagavrosh.englishsimulator.model.Dialog
import com.viktoriagavrosh.englishsimulator.model.Issue
import com.viktoriagavrosh.englishsimulator.model.Sentence

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
 * Converts [Dialog] instance to [GameQuestion] instance for ui (GameScreen)
 *
 * @return [GameQuestion] instance
 */
fun Dialog.toGameQuestion(): GameQuestion {
    return GameQuestion(
        id = id,
        question = question,
        translate = shortAnswer,
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

/**
 * Converts [GameQuestion] instance to [Dialog] instance for ui (GameScreen)
 *
 * @return [Dialog] instance
 */
fun GameQuestion.toDialog(): Dialog {
    return Dialog(
        id = id,
        question = question,
        shortAnswer = translate,
    )
}
