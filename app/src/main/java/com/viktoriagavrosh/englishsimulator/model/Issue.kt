package com.viktoriagavrosh.englishsimulator.model

/**
 * Model represents a single issue for ui
 *
 * @param id unique object identifier
 * @param englishQuestion issue text in English
 * @param russianQuestion issue text in Russian
 * @param theme theme of issue
 */
data class Issue(
    val id: Int = 0,
    val englishQuestion: String = "",
    val russianQuestion: String = "",
    val theme: String = "",
)

