package com.viktoriagavrosh.englishsimulator.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.ui.screens.repeat.RepeatScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class RepeatScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun repeatScreen_verticalScreen_backButtonIsDisplayed() {
        setRepeatScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_horizontalScreen_backButtonIsDisplayed() {
        setRepeatScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_verticalScreen_backButtonSizeIsRelevant() {
        setRepeatScreen()
        composeTestRule.onNodeWithTagById(R.string.back_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun repeatScreen_horizontalScreen_backButtonSizeIsRelevant() {
        setRepeatScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTagById(R.string.back_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun repeatScreen_verticalScreen_scoreBoxIsDisplayed() {
        setRepeatScreen(score = 54)
        composeTestRule.onNodeWithText("54")
            .assertExists("No score")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun repeatScreen_horizontalScreen_scoreBoxIsDisplayed() {
        setRepeatScreen(isVerticalScreen = false, score = 54)
        composeTestRule.onNodeWithText("54")
            .assertExists("No score")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun repeatScreen_verticalScreen_questionTextIsDisplayed() {
        setRepeatScreen(isRuToEn = true)
        composeTestRule.onNodeWithText(FakeSource.fakeSentencesUi[0].ruText)
            .assertExists("No question text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun repeatScreen_horizontalScreen_questionTextIsDisplayed() {
        setRepeatScreen(isVerticalScreen = false, isRuToEn = true)
        composeTestRule.onNodeWithText(FakeSource.fakeSentencesUi[0].ruText)
            .assertExists("No question text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun repeatScreen_verticalScreen_answerIconIsDisplayed() {
        setRepeatScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .assertExists("No answer icon")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_horizontalScreen_answerIconIsDisplayed() {
        setRepeatScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .assertExists("No answer icon")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_verticalScreen_answerTextIsDisplayed() {
        setRepeatScreen(isRuToEn = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .performClick()
        composeTestRule.onNodeWithText(FakeSource.fakeSentencesUi[0].enText)
            .assertExists()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_horizontalScreen_answerTextIsDisplayed() {
        setRepeatScreen(isVerticalScreen = false, isRuToEn = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .performClick()
        composeTestRule.onNodeWithText(FakeSource.fakeSentencesUi[0].enText)
            .assertExists()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_verticalScreen_nextButtonIsDisplayed() {
        setRepeatScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertExists("No next button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_horizontalScreen_nextButtonIsDisplayed() {
        setRepeatScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertExists("No next button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_verticalScreen_nextButtonSizeIsRelevant() {
        setRepeatScreen()
        composeTestRule.onNodeWithTagById(R.string.next_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun repeatScreen_horizontalScreen_nextButtonSizeIsRelevant() {
        setRepeatScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTagById(R.string.next_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun repeatScreen_verticalScreen_errorTextIsDisplayed() {
        setRepeatScreen(isError = true)
        composeTestRule.onNodeWithTextById(R.string.something_happened)
            .assertExists("No error text")
            .assertIsDisplayed()
    }

    @Test
    fun repeatScreen_horizontalScreen_errorTextIsDisplayed() {
        setRepeatScreen(isVerticalScreen = false, isError = true)
        composeTestRule.onNodeWithTextById(R.string.something_happened)
            .assertExists("No error text")
            .assertIsDisplayed()
    }

    @Test
    fun repeatScreen_verticalScreen_errorButtonIsDisplayed() {
        setRepeatScreen(isError = true)
        composeTestRule.onNodeWithTextById(R.string.try_again)
            .assertExists("No error button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun repeatScreen_horizontalScreen_errorButtonIsDisplayed() {
        setRepeatScreen(isVerticalScreen = false, isError = true)
        composeTestRule.onNodeWithTextById(R.string.try_again)
            .assertExists("No error button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setRepeatScreen(
        isVerticalScreen: Boolean = true,
        isError: Boolean = false,
        score: Int = 0,
        isRuToEn: Boolean = true,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                RepeatScreen(
                    modifier = Modifier.fillMaxSize(),
                    isError = isError,
                    onBackClick = {},
                    isVerticalScreen = isVerticalScreen,
                    sentence = FakeSource.fakeSentencesUi[0],
                    score = score,
                    isRuToEn = isRuToEn,
                    onNextClick = {},
                    onTryAgainClick = {},
                )
            }
        }
    }
}
