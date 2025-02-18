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
        val expectedDay = 7
        setRow(scores = mapOf(expectedDay to 0))
        composeTestRule.onNodeWithText(expectedDay.toString())
            .assertExists("No day on ScoresRow")
            .assertIsDisplayed()
    }

    @Test
    fun scoresRow_scoreIsDisplayed() {
        val expectedScore = 17
        setRow(scores = mapOf(0 to expectedScore))
        composeTestRule.onNodeWithText(expectedScore.toString())
            .assertExists("No score on ScoresRow")
            .assertIsDisplayed()
    }

    @Test
    fun scoresRow_daySliderIsDisplayed() {
        val expectedScore = 38
        setRow(scores = mapOf(1 to expectedScore))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertExists("No day slider on ScoresRow")
            .assertIsDisplayed()
    }

    @Test
    fun scoresRow_daySliderSizeIsValid() {
        val expectedScore = 58
        setRow(scores = mapOf(1 to expectedScore))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertHeightIsAtLeast((expectedScore * 2).dp)
    }

    @Test
    fun scoresRow_daySliderIsNotDisplayed() {
        val expectedScore = 0
        setRow(scores = mapOf(1 to expectedScore))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertIsNotDisplayed()
    }

    @Test
    fun scoresRow_dividerIsDisplayed() {
        setRow(scores = mapOf(1 to 1))
        composeTestRule.onNodeWithTagById(R.string.day_horizontal_divider_tag)
            .assertExists("No divider on ScoresRow")
            .assertIsDisplayed()
    }

    @Test
    fun scoresRow_largeScore_daySliderSizeIsValid() {
        val score = 160
        val expectedHeight = 200
        setRow(scores = mapOf(1 to score))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertHeightIsEqualTo(expectedHeight.dp)
    }

    @Test
    fun scoresRow_smallScore_daySliderSizeIsValid() {
        val score = -12
        val expectedHeight = 0
        setRow(scores = mapOf(1 to score))
        composeTestRule.onNodeWithTagById(R.string.one_day_on_scores_row_tag)
            .assertHeightIsEqualTo(expectedHeight.dp)
    }

    private fun setRow(
        scores: Map<Int, Int> = mapOf(1 to 24, 2 to 35, 3 to 46),
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                ScoresRow(scores = scores)
            }
        }
    }
}
