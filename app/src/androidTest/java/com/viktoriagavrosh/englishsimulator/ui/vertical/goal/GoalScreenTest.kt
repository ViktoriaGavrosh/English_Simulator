package com.viktoriagavrosh.englishsimulator.ui.vertical.goal

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.model.Goal
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.goal.GoalScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class GoalScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun goalScreen_vertical_editButtonIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertExists("No edit button")
            .assertIsDisplayed()
    }

    @Test
    fun goalScreen_vertical_editButtonHasClickAction() {
        setScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertHasClickAction()
    }

    @Test
    fun goalScreen_vertical_editButtonSizeIsRelevant() {
        setScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun goalScreen_vertical_translateQuestGoalIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .assertExists("No translate score")
            .assertIsDisplayed()
    }

    @Test
    fun goalScreen_vertical_issueQuestGoalIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .assertExists("No issue score")
            .assertIsDisplayed()
    }

    @Test
    fun goalScreen_vertical_dialogQuestGoalIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.dialog_button_title)
            .assertExists("No dialog score")
            .assertIsDisplayed()
    }

    @Test
    fun goalScreen_vertical_wordQuestGoalIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.word_button_title)
            .assertExists("No word score")
            .assertIsDisplayed()
    }

    private fun setScreen() {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                GoalScreen(
                    dayStatisticProvider = { Statistic() },
                    goal = Goal(
                        translateGoal = 12,
                        issueGoal = 14,
                        dialogGoal = 15,
                        wordGoal = 16,
                    ),
                    onEditGoalsClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
