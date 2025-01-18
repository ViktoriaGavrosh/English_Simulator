package com.viktoriagavrosh.englishsimulator.ui.menu.startmenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
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

class VerticalStartMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startMenuScreen_vertical_titleIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.app_vertical_title)
            .assertExists("No title on StartMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_titleHasNoClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.app_vertical_title)
            .assertHasNoClickAction()
    }


    @Test
    fun startMenuScreen_vertical_translateButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .assertExists("No translate button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_translateButtonHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .assertHasClickAction()
    }

    @Test
    fun startMenuScreen_vertical_issueButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .assertExists("No issue button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_issueButtonHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .assertHasClickAction()
    }

    @Test
    fun startMenuScreen_vertical_dialogButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.dialog_button_title)
            .assertExists("No dialog button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_dialogButtonHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.dialog_button_title)
            .assertHasClickAction()
    }

    @Test
    fun startMenuScreen_vertical_wordButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.word_button_title)
            .assertExists("No dialog button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_wordButtonHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.word_button_title)
            .assertHasClickAction()
    }

    @Test
    fun startMenuScreen_vertical_backButtonIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsNotDisplayed()
    }

    @Test
    fun startMenuScreen_vertical_addButtonIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .assertIsNotDisplayed()
    }

    private fun setMenuScreen() {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                StartMenuScreen(
                    isVerticalScreen = true,
                    onTranslateButtonClick = {},
                    onIssueButtonClick = {},
                    onDialogButtonClick = {},
                    onWordButtonClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
