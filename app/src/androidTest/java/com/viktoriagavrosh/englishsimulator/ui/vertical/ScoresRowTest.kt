package com.viktoriagavrosh.englishsimulator.ui.vertical

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.ScoresRow
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import org.junit.Rule
import org.junit.Test

class ScoresRowTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun scoresRow_dayIsDisplayed() {
        val expectedDay = 1
        setRow()
        composeTestRule.onNodeWithText(expectedDay.toString())
            .assertExists("No day on ScoresRow")
            .assertIsDisplayed()
    }

    @Test
    fun scoresRow_scoreIsDisplayed() {
        val expectedScore = 17
        setRow(scores = listOf(expectedScore))
        composeTestRule.onNodeWithText(expectedScore.toString())
            .assertExists("No score on ScoresRow")
            .assertIsDisplayed()
    }

    @Test
    fun scoresRow_daySliderIsDisplayed() {
        val expectedScore = 38
        setRow(scores = listOf(expectedScore))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertExists("No day slider on ScoresRow")
            .assertIsDisplayed()
    }

    @Test
    fun scoresRow_daySliderSizeIsValid() {
        val expectedScore = 58
        setRow(scores = listOf(expectedScore))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertHeightIsAtLeast((expectedScore * 2).dp)
    }

    @Test
    fun scoresRow_daySliderIsNotDisplayed() {
        val expectedScore = 0
        setRow(scores = listOf(expectedScore))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertIsNotDisplayed()
    }

    @Test
    fun scoresRow_dividerIsDisplayed() {
        setRow(scores = listOf(5))
        composeTestRule.onNodeWithTagById(R.string.day_horizontal_divider_tag)
            .assertExists("No divider on ScoresRow")
            .assertIsDisplayed()
    }

    @Test
    fun scoresRow_largeScore_daySliderSizeIsValid() {
        val score = 160
        val expectedHeight = 200
        setRow(scores = listOf(score))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertHeightIsEqualTo(expectedHeight.dp)
    }

    @Test
    fun scoresRow_smallScore_daySliderSizeIsValid() {
        val score = -12
        val expectedHeight = 0
        setRow(scores = listOf(score))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertHeightIsEqualTo(expectedHeight.dp)
    }

    private fun setRow(
        scores: List<Int> = listOf(24, 35, 46),
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                ScoresRow(scores = scores)
            }
        }
    }
}
