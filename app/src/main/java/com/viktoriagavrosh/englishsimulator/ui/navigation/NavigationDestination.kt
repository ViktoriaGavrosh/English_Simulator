package com.viktoriagavrosh.englishsimulator.ui.navigation

import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest.EnToRu
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest.RuToEn
import kotlinx.serialization.Serializable

/**
 * Describes destinations for navigation between app screens
 */
sealed class NavigationDestination {

    /**
     * Describes navigation destination of MenuScreen
     */
    @Serializable
    data object Menu : NavigationDestination()

    /**
     * Describes navigation destination of RepeatScreen
     *
     * @param quest constant describes what action will be shown by Ui
     */
    @Serializable
    data class Translate(val quest: Quest = Quest.RuToEn) : NavigationDestination()
}

/**
 * Constants describes what action will be shown by Ui
 *
 * @see RuToEn
 * @see EnToRu
 */
enum class Quest {

    /**
     * Quest "Translate from Russian into English"
     */
    RuToEn,

    /**
     * Quest "Translate from English into Russian"
     */
    EnToRu
}
