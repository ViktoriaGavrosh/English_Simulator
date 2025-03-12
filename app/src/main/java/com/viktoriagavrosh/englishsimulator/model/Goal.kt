package com.viktoriagavrosh.englishsimulator.model

/**
 * Model represents a single item for data from Datastore Preferences
 *
 * @param translateGoal daily goal for quest "Translate sentences"
 * @param issueGoal daily goal for quest "Tell about yourself"
 * @param dialogGoal daily goal for quest "Short dialogs"
 * @param wordGoal daily goal for quest "FlashCards"
 */
data class Goal(
    val translateGoal: Int = 0,
    val issueGoal: Int = 0,
    val dialogGoal: Int = 0,
    val wordGoal: Int = 0,
)
