package com.viktoriagavrosh.englishsimulator.ui.vertical.update

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordUpdateScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class UpdateScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun updateScreen_alertDialog_saveButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.save)
            .assertExists("No save button")
            .assertIsDisplayed()
    }

    @Test
    fun updateScreen_alertDialog_saveButtonHasClickAction() {
        setMenuScreen(isWordValid = true)
        composeTestRule.onNodeWithTextById(R.string.save)
            .assertHasClickAction()
    }

    @Test
    fun updateScreen_alertDialog_saveButtonNotEnabled() {
        setMenuScreen(isWordValid = false)
        composeTestRule.onNodeWithTextById(R.string.save)
            .assertIsNotEnabled()
    }

    @Test
    fun updateScreen_alertDialog_saveButtonSizeIsRelevant() {
        setMenuScreen(isWordValid = true)
        composeTestRule.onNodeWithTagById(R.string.save_button_tag)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun updateScreen_alertDialog_cancelButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.cancel)
            .assertExists("No cancel button")
            .assertIsDisplayed()
    }

    @Test
    fun updateScreen_alertDialog_cancelButtonHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.cancel)
            .assertHasClickAction()
    }

    @Test
    fun updateScreen_alertDialog_cancelButtonSizeIsRelevant() {
        setMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.cancel_button_tag)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun updateScreen_alertDialog_deleteIconIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.delete_word)
            .assertExists("No delete icon")
            .assertIsDisplayed()
    }

    @Test
    fun updateScreen_alertDialog_deleteIconHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.delete_word)
            .assertHasClickAction()
    }

    @Test
    fun updateScreen_alertDialog_deleteIconSizeIsRelevant() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.delete_word)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun updateScreen_alertDialog_englishTextHintIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.english_text)
            .assertExists("No english text hint")
            .assertIsDisplayed()
    }

    @Test
    fun updateScreen_alertDialog_englishTextFieldHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.english_text)
            .assertHasClickAction()
    }

    @Test
    fun updateScreen_alertDialog_englishTextIsDisplayed() {
        val text = "new text"
        setMenuScreen(englishText = text)
        composeTestRule.onNodeWithText(text)
            .assertExists("No english text")
            .assertIsDisplayed()
    }

    @Test
    fun updateScreen_alertDialog_russianTextHintIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.russian_text)
            .assertExists("No russian text hint")
            .assertIsDisplayed()
    }

    @Test
    fun updateScreen_alertDialog_russianTextFieldHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.russian_text)
            .assertHasClickAction()
    }

    @Test
    fun updateScreen_alertDialog_russianTextIsDisplayed() {
        val text = "new text"
        setMenuScreen(russianText = text)
        composeTestRule.onNodeWithText(text)
            .assertExists("No russian text")
            .assertIsDisplayed()
    }

    @Test
    fun updateScreen_alertDialog_themeHintIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.theme)
            .assertExists("No theme hint")
            .assertIsDisplayed()
    }

    @Test
    fun updateScreen_alertDialog_themeFieldHasClickAction() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.theme)
            .assertHasClickAction()
    }

    @Test
    fun updateScreen_alertDialog_themeIsDisplayed() {
        val newTheme = "new theme"
        setMenuScreen(theme = newTheme)
        composeTestRule.onNodeWithText(newTheme)
            .assertExists("No theme")
            .assertIsDisplayed()
    }

    private fun setMenuScreen(
        englishText: String = "",
        russianText: String = "",
        theme: String = "",
        isWordValid: Boolean = false,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                WordUpdateScreen(
                    englishTextProvider = { englishText },
                    russianTextProvider = { russianText },
                    themeProvider = { theme },
                    isWordValidProvider = { isWordValid },
                    onEnglishTextChange = {},
                    onRussianTextChange = {},
                    onThemeChange = {},
                    onSaveClick = {},
                    onDeleteClick = {},
                    onBackClick = {},
                )
            }
        }
    }
}
