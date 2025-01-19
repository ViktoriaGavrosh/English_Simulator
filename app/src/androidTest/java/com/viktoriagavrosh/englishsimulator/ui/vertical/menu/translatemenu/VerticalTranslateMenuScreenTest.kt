package com.viktoriagavrosh.englishsimulator.ui.vertical.menu.translatemenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class VerticalTranslateMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun translateMenuScreen_vertical_titleIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.translate_vertical_title)
            .assertExists("No title on TranslateMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_vertical_toEnglishButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .assertExists("No translate to English button")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_vertical_toRussianButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.en_to_ru)
            .assertExists("No translate to Russian button")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_vertical_backButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_vertical_addButtonIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .assertIsNotDisplayed()
    }

    @Test
    fun translateMenuScreen_vertical_dropdownMenuIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.dropdown_menu_tag)
            .assertIsNotDisplayed()
    }

    private fun setMenuScreen() {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                TranslateMenuScreen(
                    isVerticalScreen = true,
                    onToEnglishButtonClick = {},
                    onToRussianButtonClick = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
