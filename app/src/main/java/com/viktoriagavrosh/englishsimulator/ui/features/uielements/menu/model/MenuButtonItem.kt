package com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.model

/**
 * Model represents a single item for ui (MenuScreen)
 *
 * @param title text on Button
 * @param onClick callback that is executed when button is clicked
 */
data class MenuButtonItem(
    val title: String = "",
    val onClick: () -> Unit = {},
)
