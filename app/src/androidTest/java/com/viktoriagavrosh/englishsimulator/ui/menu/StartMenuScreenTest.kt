package com.viktoriagavrosh.englishsimulator.ui.menu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.StartMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class StartMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startMenuScreen_vertical_titleIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.app_vertical_title)
            .assertExists("No title on StartMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_titleIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.app_horizontal_title)
            .assertExists("No title on StartMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_translateButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .assertExists("No translate button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_translateButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .assertExists("No translate button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_issueButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .assertExists("No issue button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_issueButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .assertExists("No issue button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_backButtonIsNotDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsNotDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_backButtonIsNotDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsNotDisplayed()
    }

    private fun setMenuScreen(isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                StartMenuScreen(
                    isVerticalScreen = isVerticalScreen,
                    onTranslateButtonClick = {},
                    onIssueButtonClick = {},
                    onDialogButtonClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
