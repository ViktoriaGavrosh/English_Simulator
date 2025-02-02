package com.viktoriagavrosh.englishsimulator.model

import com.viktoriagavrosh.englishsimulator.model.Question.Dialog
import com.viktoriagavrosh.englishsimulator.model.Question.Issue
import com.viktoriagavrosh.englishsimulator.model.Question.Sentence
import com.viktoriagavrosh.englishsimulator.model.Question.Word

/**
 * Model represents a single question for quests
 *
 * @param id unique object identifier
 * @param questionText text for question
 * @param answerText text for answer
 * @param theme theme of question
 * @see Sentence
 * @see Issue
 * @see Dialog
 * @see Word
 */
sealed class Question(
    open val id: Int = 0,
    open val questionText: String = "",
    open val answerText: String = "",
    open val theme: String = "",
) {
    /**
     * Model represents a single question for quest "Translate sentences"
     *
     * @param id unique object identifier
     * @param questionText sentence text in English
     * @param answerText sentence text in Russian
     */
    data class Sentence(
        override val id: Int = 0,
        override val questionText: String = "",
        override val answerText: String = "",
    ) : Question(theme = "")

    /**
     * Model represents a single question for quest "Tell about yourself"
     *
     * @param id unique object identifier
     * @param questionText issue text in English
     * @param answerText issue text in Russian
     * @param theme theme of issue
     */
    data class Issue(
        override val id: Int = 0,
        override val questionText: String = "",
        override val answerText: String = "",
        override val theme: String = "",
    ) : Question()

    /**
     * Model represents a single question for quest "Short dialogs"
     *
     * @param id unique object identifier
     * @param questionText text of dialog question
     * @param answerText text of dialog answer
     */
    data class Dialog(
        override val id: Int = 0,
        override val questionText: String = "",
        override val answerText: String = "",
    ) : Question(theme = "")

    /**
     * Model represents a single question for quest "FlashCards"
     *
     * @param id unique object identifier
     * @param questionText word in English
     * @param answerText word in Russian
     * @param theme theme of word
     */
    data class Word(
        override val id: Int = 0,
        override val questionText: String = "",
        override val answerText: String = "",
        override val theme: String = "",
    ) : Question()
}
