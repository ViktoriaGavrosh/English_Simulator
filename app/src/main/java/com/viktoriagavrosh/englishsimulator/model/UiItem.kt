package com.viktoriagavrosh.englishsimulator.model

/**
 * Model represents a single item for ui
 *
 * @param id unique object identifier
 * @param questionText text for question field
 * @param answerText text for answer field
 * @param theme theme of word
 * @param quizName Constant describes what quiz item for
 */
data class UiItem(
    val id: Int = 0,
    val questionText: String = "",
    val answerText: String = "",
    val theme: String = "",
    val quizName: QuizName = QuizName.Translate
)
