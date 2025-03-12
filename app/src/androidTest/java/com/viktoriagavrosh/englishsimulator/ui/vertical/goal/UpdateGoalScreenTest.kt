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
import com.viktoriagavrosh.englishsimulator.ui.features.goal.UpdateGoalScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class UpdateGoalScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun updateGoalScreen_vertical_backButtonIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button")
            .assertIsDisplayed()
    }

    @Test
    fun updateGoalScreen_vertical_backButtonHasClickAction() {
        setScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertHasClickAction()
    }

    @Test
    fun updateGoalScreen_vertical_backButtonSizeIsRelevant() {
        setScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun updateGoalScreen_vertical_titleIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.daily_goal_title)
            .assertExists("No daily goal title")
            .assertIsDisplayed()
    }

    @Test
    fun updateGoalScreen_vertical_translateQuestGoalIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .assertExists("No translate goal")
            .assertIsDisplayed()
    }

    @Test
    fun updateGoalScreen_vertical_issueQuestGoalIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .assertExists("No issue goal")
            .assertIsDisplayed()
    }

    @Test
    fun updateGoalScreen_vertical_dialogQuestGoalIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.dialog_button_title)
            .assertExists("No dialog goal")
            .assertIsDisplayed()
    }

    @Test
    fun updateGoalScreen_vertical_wordQuestGoalIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.word_button_title)
            .assertExists("No word goal")
            .assertIsDisplayed()
    }

    private fun setScreen() {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                UpdateGoalScreen(
                    goal = Goal(
                        translateGoal = 4,
                        issueGoal = 6,
                        dialogGoal = 8,
                        wordGoal = 9
                    ),
                    onTranslateGoalUpdate = {},
                    onIssueGoalUpdate = {},
                    onDialogGoalUpdate = {},
                    onWordGoalUpdate = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
