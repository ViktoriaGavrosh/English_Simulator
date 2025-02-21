package com.viktoriagavrosh.englishsimulator.model

/**
 * Constants describes what model is it
 *
 * @see Sentence
 * @see Issue
 * @see Dialog
 * @see Word
 */
enum class ModelName {
    /**
     * Name of model represents a single sentence for quest "Translate sentences"
     */
    Sentence,

    /**
     * Name of model represents a single issue for quest "Tell about yourself"
     */
    Issue,

    /**
     * Name of model represents a single dialog for quest "Short dialogs"
     */
    Dialog,

    /**
     * Name of model represents a single word for quest "FlashCards"
     */
    Word
}
