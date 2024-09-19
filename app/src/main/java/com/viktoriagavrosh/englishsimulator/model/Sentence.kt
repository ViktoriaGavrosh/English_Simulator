package com.viktoriagavrosh.englishsimulator.model

/**
 * Model represents a single sentence for ui
 *
 * @param id unique object identifier
 * @param ruText sentence text in Russian
 * @param enText sentence text in English
 */
data class Sentence(
    val id: Int = 0,
    val ruText: String = "",
    val enText: String = "",
)
