package com.viktoriagavrosh.englishsimulator.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class MenuScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun menuScreen_verticalScreen_appTitleIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.app_title)
            .assertExists("No app title")
            .assertIsDisplayed()
    }

    @Test
    fun menuScreen_horizontalScreen_appTitleIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.app_title)
            .assertExists("No app title")
            .assertIsDisplayed()
    }

    @Test
    fun menuScreen_verticalScreen_topButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .assertExists("No top button - ru to en")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun menuScreen_horizontalScreen_topButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .assertExists("No top button - ru to en")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun menuScreen_verticalScreen_bottomButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.en_to_ru)
            .assertExists("No top button - en to ru")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun menuScreen_horizontalScreen_bottomButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.en_to_ru)
            .assertExists("No top button - en to ru")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun menuScreen_verticalScreen_topButtonSizeIsRelevant() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun menuScreen_horizontalScreen_topButtonSizeIsRelevant() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun menuScreen_verticalScreen_bottomButtonSizeIsRelevant() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.en_to_ru)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun menuScreen_horizontalScreen_bottomButtonSizeIsRelevant() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.en_to_ru)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setMenuScreen(isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                MenuScreen(
                    isVerticalScreen = isVerticalScreen,
                    onSecondButtonClick = {},
                    onFirstButtonClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
