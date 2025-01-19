package com.viktoriagavrosh.englishsimulator.ui.horizontal.menu.startmenu

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
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class HorizontalStartMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startMenuScreen_horizontal_titleIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.app_horizontal_title)
            .assertExists("No title on StartMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_translateButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .assertExists("No translate button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_issueButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .assertExists("No issue button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_dialogButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.dialog_button_title)
            .assertExists("No dialog button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_wordButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.word_button_title)
            .assertExists("No dialog button")
            .assertIsDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_backButtonIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsNotDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_addButtonIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .assertIsNotDisplayed()
    }

    @Test
    fun startMenuScreen_horizontal_dropdownMenuIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.dropdown_menu_tag)
            .assertIsNotDisplayed()
    }

    private fun setMenuScreen() {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                StartMenuScreen(
                    isVerticalScreen = false,
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
