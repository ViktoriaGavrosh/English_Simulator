package com.viktoriagavrosh.englishsimulator.ui.vertical

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.StatisticScreen
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.toMapScores
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class StatisticScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun statisticScreen_lastMonthIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.last_month)
            .assertExists("No last month tab on StatisticScreen")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_thisMonthIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.this_month)
            .assertExists("No this month tab on StatisticScreen")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_lastMonthIsNotSelected() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.last_month)
            .assertIsNotSelected()
    }

    @Test
    fun statisticScreen_thisMonthIsSelected() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.this_month)
            .assertIsSelected()
    }

    @Test
    fun statisticScreen_lastMonthIsSelected() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.last_month)
            .performClick()
            .assertIsSelected()
    }

    @Test
    fun statisticScreen_thisMonthIsNotSelected() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.last_month)
            .performClick()
        composeTestRule.onNodeWithTextById(R.string.this_month)
            .assertIsNotSelected()
    }

    @Test
    fun statisticScreen_lastMonthSizeIsRelevant() {
        setScreen()
        composeTestRule.onNodeWithTagById(R.string.last_month)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun statisticScreen_thisMonthSizeIsRelevant() {
        setScreen()
        composeTestRule.onNodeWithTagById(R.string.this_month)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun statisticScreen_translateTextIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .assertExists("No translate text")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_issueTextIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .assertExists("No issue text")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_dialogTextIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTagById(R.string.statistic_column_tag)
            .performTouchInput { this.swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.dialog_button_title)
            .assertExists("No dialog text")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_wordTextIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTagById(R.string.statistic_column_tag)
            .performTouchInput { this.swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.word_button_title)
            .assertExists("No word text")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_translateScoresRowIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTagById(R.string.translate_button_title)
            .assertExists("No translate scores row")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_issueScoresRowIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTagById(R.string.issue_button_title)
            .assertExists("No issue scores row")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_dialogScoresRowIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTagById(R.string.dialog_button_title)
            .assertExists("No dialog scores row")
            .assertIsDisplayed()
    }

    @Test
    fun statisticScreen_wordScoresRowIsDisplayed() {
        setScreen()
        composeTestRule.onNodeWithTagById(R.string.word_button_title)
            .assertExists("No word scores row")
            .assertIsDisplayed()
    }

    private fun setScreen(
        statistics: List<Statistic> = emptyList(),
        isError: Boolean = false,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                StatisticScreen(
                    translateScoresProvider = { statistics.toMapScores { it.translateScore } },
                    issueScoresProvider = { statistics.toMapScores { it.issueScore } },
                    dialogScoresProvider = { statistics.toMapScores { it.dialogScore } },
                    wordScoresProvider = { statistics.toMapScores { it.wordScore } },
                    isErrorProvider = { isError },
                    onTabClick = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
