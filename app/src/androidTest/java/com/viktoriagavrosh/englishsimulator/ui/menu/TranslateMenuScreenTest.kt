package com.viktoriagavrosh.englishsimulator.ui.menu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class TranslateMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun translateMenuScreen_vertical_titleIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.translate_vertical_title)
            .assertExists("No title on TranslateMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_horizontal_titleIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.translate_horizontal_title)
            .assertExists("No title on TranslateMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_vertical_toEnglishButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .assertExists("No translate to English button")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_horizontal_toEnglishButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .assertExists("No translate to English button")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_vertical_toRussianButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.en_to_ru)
            .assertExists("No translate to Russian button")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_horizontal_toRussianButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.en_to_ru)
            .assertExists("No translate to Russian button")
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_vertical_backButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsDisplayed()
    }

    @Test
    fun translateMenuScreen_horizontal_backButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsDisplayed()
    }

    private fun setMenuScreen(isVerticalScreen: Boolean) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                TranslateMenuScreen(
                    isVerticalScreen = isVerticalScreen,
                    onToEnglishButtonClick = {},
                    onToRussianButtonClick = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
