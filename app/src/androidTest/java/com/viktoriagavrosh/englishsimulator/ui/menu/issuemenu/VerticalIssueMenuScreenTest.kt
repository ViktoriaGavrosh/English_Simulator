package com.viktoriagavrosh.englishsimulator.ui.menu.issuemenu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class VerticalIssueMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun issueMenuScreen_vertical_titleIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.issue_vertical_title)
            .assertExists("No title on IssueMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_vertical_backButtonIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_vertical_addButtonIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .assertIsNotDisplayed()
    }

    @Test
    fun issueMenuScreen_vertical_dropdownMenuIsNotDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.dropdown_menu_tag)
            .assertIsNotDisplayed()
    }

    @Test
    fun issueMenuScreen_vertical_allThemeCardIsDisplayed() {
        setMenuScreen()
        composeTestRule.onNodeWithTextById(R.string.all_themes)
            .assertExists("No all card on IssueMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_vertical_customCardsIsDisplayed() {
        val themes = listOf("one", "two", "three")

        setMenuScreen(themes = themes)
        composeTestRule.onNodeWithText(themes[0])
            .assertExists("No one card on IssueMenuScreen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(themes[1])
            .assertExists("No two card on IssueMenuScreen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(themes[2])
            .assertExists("No three card on IssueMenuScreen")
            .assertIsDisplayed()
    }

    private fun setMenuScreen(
        themes: List<String> = listOf("Theme"),
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                IssueMenuScreen(
                    screenStateProvider = { RequestResult.Success(themes) },
                    isVerticalScreen = true,
                    onButtonClick = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
