package com.viktoriagavrosh.englishsimulator.ui.vertical.goal

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.goal.elements.GoalRow
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
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

    @Test
    fun goalRow_increaseButtonIsDisplayed() {
        setRow()
        composeTestRule.onNodeWithContentDescriptionById(R.string.increase_score)
            .assertExists("No increase button")
            .assertIsDisplayed()
    }

    @Test
    fun goalRow_increaseButtonHasClickAction() {
        setRow()
        composeTestRule.onNodeWithContentDescriptionById(R.string.increase_score)
            .assertHasClickAction()
    }

    @Test
    fun goalRow_decreaseButtonIsDisplayed() {
        setRow()
        composeTestRule.onNodeWithContentDescriptionById(R.string.decrease_score)
            .assertExists("No decrease button")
            .assertIsDisplayed()
    }

    @Test
    fun goalRow_decreaseButtonHasClickAction() {
        setRow()
        composeTestRule.onNodeWithContentDescriptionById(R.string.decrease_score)
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
