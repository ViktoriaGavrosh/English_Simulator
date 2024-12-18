package com.viktoriagavrosh.englishsimulator.ui.menu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class IssueMenuScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun issueMenuScreen_vertical_titleIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.issue_vertical_title)
            .assertExists("No title on IssueMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_horizontal_titleIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.issue_horizontal_title)
            .assertExists("No title on IssueMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_vertical_backButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_horizontal_backButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_vertical_allThemeButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = true)
        composeTestRule.onNodeWithTextById(R.string.all_themes)
            .assertExists("No all button on IssueMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_horizontal_allThemeButtonIsDisplayed() {
        setMenuScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTextById(R.string.all_themes)
            .assertExists("No all button on IssueMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_vertical_customButtonsIsDisplayed() {
        val themes = listOf("one", "two", "three")

        setMenuScreen(
            isVerticalScreen = true,
            themes = themes,
        )
        composeTestRule.onNodeWithText(themes[0])
            .assertExists("No one button on IssueMenuScreen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(themes[1])
            .assertExists("No two button on IssueMenuScreen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(themes[2])
            .assertExists("No three button on IssueMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueMenuScreen_horizontal_customButtonsIsDisplayed() {
        val themes = listOf("one", "two", "three")

        setMenuScreen(
            isVerticalScreen = false,
            themes = themes,
        )
        composeTestRule.onNodeWithText(themes[0])
            .assertExists("No one button on IssueMenuScreen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(themes[1])
            .assertExists("No two button on IssueMenuScreen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(themes[2])
            .assertExists("No three button on IssueMenuScreen")
            .assertIsDisplayed()
    }

    private fun setMenuScreen(
        isVerticalScreen: Boolean,
        themes: List<String> = listOf("Theme"),
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                IssueMenuScreen(
                    screenStateProvider = { RequestResult.Success(themes) },
                    isVerticalScreen = isVerticalScreen,
                    onButtonClick = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
