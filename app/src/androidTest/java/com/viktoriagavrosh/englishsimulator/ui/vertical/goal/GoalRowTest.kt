package com.viktoriagavrosh.englishsimulator.ui.vertical.goal

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.englishsimulator.ui.features.goal.elements.GoalRow
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import org.junit.Rule
import org.junit.Test

class GoalRowTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun goalRow_titleIsDisplayed() {
        val expected = "New title"
        setRow(title = expected)
        composeTestRule.onNodeWithText(expected)
            .assertExists("No title")
            .assertIsDisplayed()
    }

    @Test
    fun goalRow_goalIsDisplayed() {
        val expected = 18
        setRow(goalScore = expected)
        composeTestRule.onNodeWithText(expected.toString())
            .assertExists("No goal")
            .assertIsDisplayed()
    }

    @Test
    fun goalRow_goalHasClickAction() {
        val expected = 18
        setRow(goalScore = expected)
        composeTestRule.onNodeWithText(expected.toString())
            .assertHasClickAction()
    }

    private fun setRow(
        title: String = "Title",
        goalScore: Int = 0,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                GoalRow(
                    title = title,
                    goalScore = goalScore,
                    onGoalUpdate = {},
                )
            }
        }
    }
}
