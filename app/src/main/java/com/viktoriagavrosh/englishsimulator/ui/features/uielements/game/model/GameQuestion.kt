package com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.model

import com.viktoriagavrosh.englishsimulator.model.Issue
import com.viktoriagavrosh.englishsimulator.model.Sentence

data class GameQuestion(
    val id: Int = 0,
    val question: String = "",
    val translate: String = "",
)

fun Sentence.toGameQuestion(isToEnglish: Boolean = true): GameQuestion {
    val question = if (isToEnglish) ruText else enText
    val translate = if (isToEnglish) enText else ruText

    return GameQuestion(
        id = id,
        question = question,
        translate = translate,
    )
}

fun Issue.toGameQuestion(): GameQuestion {
    return GameQuestion(
        id = id,
        question = englishQuestion,
        translate = russianQuestion,
    )
}
