package com.viktoriagavrosh.englishsimulator.ui.vertical.goal

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.goal.elements.QuestGoal
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import org.junit.Rule
import org.junit.Test

class QuestGoalTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun questGoal_titleIsDisplayed() {
        val expected = "New title"
        setRow(title = expected)
        composeTestRule.onNodeWithText(expected)
            .assertExists("No title on QuestGoal")
            .assertIsDisplayed()
    }

    @Test
    fun questGoal_goalIsDisplayed() {
        val expected = 18
        setRow(goalScore = expected)
        composeTestRule.onNodeWithText(expected.toString())
            .assertExists("No goal on QuestGoal")
            .assertIsDisplayed()
    }

    @Test
    fun questGoal_sliderIsDisplayed() {
        setRow()
        composeTestRule.onNodeWithTagById(R.string.goal_slider_tag)
            .assertExists("No slider on QuestGoal")
            .assertIsDisplayed()
    }

    private fun setRow(
        title: String = "Title",
        goalScore: Int = 10,
        questScore: Int = 5,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                QuestGoal(
                    title = title,
                    goalScore = goalScore,
                    questScore = questScore,
                )
            }
        }
    }
}
