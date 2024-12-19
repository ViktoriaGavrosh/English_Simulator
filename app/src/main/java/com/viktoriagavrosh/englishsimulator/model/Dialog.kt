package com.viktoriagavrosh.englishsimulator.model

/**
 * Model represents a single dialog given for ui
 *
 * @param id unique object identifier
 * @param question text of dialog question
 * @param shortAnswer text of dialog answer
 */
data class Dialog(
    val id: Int = 0,
    val question: String = "",
    val shortAnswer: String = "",
)

