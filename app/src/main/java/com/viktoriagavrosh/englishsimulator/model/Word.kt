package com.viktoriagavrosh.englishsimulator.model

/**
 * Model represents a single word given from database
 *
 * @param id unique object identifier
 * @param englishWord word in Russian
 * @param russianWord word in English
 * @param theme theme of word
 */
data class Word(
    val id: Int = 0,
    val englishWord: String = "",
    val russianWord: String = "",
    val theme: String = "",
)

