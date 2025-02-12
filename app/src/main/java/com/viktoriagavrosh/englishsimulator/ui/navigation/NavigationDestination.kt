package com.viktoriagavrosh.englishsimulator.ui.navigation

import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest.RuToEn
import kotlinx.serialization.Serializable

/**
 * Describes destinations for navigation between app screens
 */
sealed class NavigationDestination {

    /**
     * Describes navigation destination of StartMenuScreen
     */
    @Serializable
    data object StartMenu : NavigationDestination()

    /**
     * Describes navigation destination of TranslateMenuScreen
     */
    @Serializable
    data object TranslateMenu : NavigationDestination()

    /**
     * Describes navigation destination of IssueMenuScreen
     */
    @Serializable
    data object IssueMenu : NavigationDestination()

    /**
     * Describes navigation destination of WordMenuScreen
     */
    @Serializable
    data object WordMenu : NavigationDestination()

    /**
     * Describes navigation destination of TranslateGameScreen
     *
     * @param quest constant describes what action will be shown by Ui
     */
    @Serializable
    data class TranslateGame(val quest: Quest = RuToEn) : NavigationDestination()

    /**
     * Describes navigation destination of IssueGameScreen
     *
     * @param theme describes what action will be shown by Ui
     */
    @Serializable
    data class IssueGame(val theme: String = "") : NavigationDestination()

    /**
     * Describes navigation destination of DialogGameScreen
     */
    @Serializable
    data object DialogGame : NavigationDestination()

    /**
     * Describes navigation destination of WordGameScreen
     *
     * @param theme describes what action will be shown by Ui
     * @param quest describes what action will be shown by Ui
     */
    @Serializable
    data class WordGame(
        val theme: String = "",
        val quest: Quest = RuToEn
    ) : NavigationDestination()

    /**
     * Describes navigation destination of IssueGameScreen
     *
     * @param wordId describes what action will be shown by Ui
     */
    @Serializable
    data class WordUpdate(val wordId: Int = 0) : NavigationDestination()

    /**
     * Describes navigation destination of StatisticScreen
     */
    @Serializable
    data object Statistic : NavigationDestination()
}
