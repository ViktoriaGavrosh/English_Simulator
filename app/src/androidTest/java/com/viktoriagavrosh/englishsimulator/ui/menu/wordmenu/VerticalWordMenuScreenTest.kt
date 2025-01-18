package com.viktoriagavrosh.englishsimulator.ui.menu.wordmenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class VerticalWordMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun wordMenuScreen_vertical_titleIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.word_vertical_title)
            .assertExists("No title on WordMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordMenuScreen_vertical_backButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No backButton on WordMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordMenuScreen_vertical_addButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .assertExists("No addButton on WordMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordMenuScreen_vertical_allThemeCardIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.all_themes)
            .assertExists("No all card on WordMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordMenuScreen_vertical_customCardsIsDisplayed() {
        val themes = listOf("card 1", "card 2", "card 3")

        setMenuScreen(themes = themes)
        composeTestRule.onNodeWithText(themes[0])
            .assertExists("No one card on WordMenuScreen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(themes[1])
            .assertExists("No two card on WordMenuScreen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(themes[2])
            .assertExists("No three card on WordMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordMenuScreen_vertical_dropdownMenuIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.dropdown_menu_tag)
            .assertExists("No dropdownMenu on WordMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordMenuScreen_vertical_textOnDropdownMenuIsDisplayed() {
        val options = listOf("first", "second")
        setMenuScreen(
            dropdownMenuOptions = options,
            selectedOption = options[0]
        )
        composeTestRule.onNodeWithText(options[0])
            .assertExists("No text on dropdownMenu on WordMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordMenuScreen_vertical_secondTextOnDropdownMenuIsNotDisplayed() {
        val options = listOf("first", "second")
        setMenuScreen(
            dropdownMenuOptions = options,
            selectedOption = options[0]
        )
        composeTestRule.onNodeWithText(options[1])
            .assertIsNotDisplayed()
    }

    @Test
    fun wordMenuScreen_vertical_secondTextOnDropdownMenuIsDisplayed() {
        val options = listOf("first", "second")
        setMenuScreen(
            dropdownMenuOptions = options,
            selectedOption = options[0]
        )
        composeTestRule.onNodeWithText(options[0]).performClick()
        composeTestRule.onNodeWithText(options[1])
            .assertExists("No second text on dropdownMenu on WordMenuScreen")
            .assertIsDisplayed()
    }

    private fun setMenuScreen(
        themes: List<String> = listOf("Theme"),
        dropdownMenuOptions: List<String> = listOf("one", "two"),
        selectedOption: String = "one",
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                WordMenuScreen(
                    screenStateProvider = { RequestResult.Success(themes) },
                    isVerticalScreen = true,
                    onButtonClick = {},
                    onBackClick = {},
                    dropdownMenuSelectedOptionProvider = { selectedOption },
                    dropdownMenuOptions = dropdownMenuOptions,
                    onDropdownMenuValueChange = {},
                    onAddButtonClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
