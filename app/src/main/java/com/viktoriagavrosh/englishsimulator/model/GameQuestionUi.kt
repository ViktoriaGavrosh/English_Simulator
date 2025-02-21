package com.viktoriagavrosh.englishsimulator.model

/**
 * Model represents a single item for ui (GameScreen)
 *
 * @param id unique object identifier
 * @param question text in English
 * @param translate text in Russian
 */
data class GameQuestionUi(
    val id: Int = 0,
    val question: String = "",
    val translate: String = "",
)
