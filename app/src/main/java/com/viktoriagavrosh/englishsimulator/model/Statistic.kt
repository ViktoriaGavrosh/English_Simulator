package com.viktoriagavrosh.englishsimulator.model

/**
 * Model represents a single statistic object for UI
 *
 * @param id unique object identifier
 * @param date day
 * @param translateScore score of quest "Translate sentences"
 * @param issueScore score of quest "Tell about yourself"
 * @param dialogScore score of quest "Short dialogs"
 * @param wordScore score of quest "FlashCards"
 */
data class Statistic(
    val id: Int = 0,
    val date: String = "",
    val translateScore: Int = 0,
    val issueScore: Int = 0,
    val dialogScore: Int = 0,
    val wordScore: Int = 0,
)
