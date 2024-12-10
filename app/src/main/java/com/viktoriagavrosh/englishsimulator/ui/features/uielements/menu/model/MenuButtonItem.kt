package com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.model

data class MenuButtonItem(
    val title: String = "",
    val onClick: () -> Unit = {},
)
