package com.viktoriagavrosh.englishsimulator.ui.navigation

import kotlinx.serialization.Serializable

sealed class NavigationDestination {
    @Serializable
    data object Menu : NavigationDestination()

    @Serializable
    data class Repeat(val quest: Quest = Quest.RuToEn) : NavigationDestination()
}

enum class Quest {
    RuToEn,
    EnToRu
}
