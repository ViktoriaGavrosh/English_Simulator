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
import com.viktoriagavrosh.englishsimulator.ui.screens.translate.TranslateScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class TranslateScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun translateScreen_verticalScreen_backButtonIsDisplayed() {
        setTranslateScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_horizontalScreen_backButtonIsDisplayed() {
        setTranslateScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_verticalScreen_backButtonSizeIsRelevant() {
        setTranslateScreen()
        composeTestRule.onNodeWithTagById(R.string.back_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun translateScreen_horizontalScreen_backButtonSizeIsRelevant() {
        setTranslateScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTagById(R.string.back_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun translateScreen_verticalScreen_scoreBoxIsDisplayed() {
        setTranslateScreen(score = 54)
        composeTestRule.onNodeWithText("54")
            .assertExists("No score")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun translateScreen_horizontalScreen_scoreBoxIsDisplayed() {
        setTranslateScreen(isVerticalScreen = false, score = 54)
        composeTestRule.onNodeWithText("54")
            .assertExists("No score")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun translateScreen_verticalScreen_questionTextIsDisplayed() {
        setTranslateScreen(isRuToEn = true)
        composeTestRule.onNodeWithText(FakeSource.fakeSentencesUi[0].ruText)
            .assertExists("No question text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun translateScreen_horizontalScreen_questionTextIsDisplayed() {
        setTranslateScreen(isVerticalScreen = false, isRuToEn = true)
        composeTestRule.onNodeWithText(FakeSource.fakeSentencesUi[0].ruText)
            .assertExists("No question text")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }

    @Test
    fun translateScreen_verticalScreen_answerIconIsDisplayed() {
        setTranslateScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .assertExists("No answer icon")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_horizontalScreen_answerIconIsDisplayed() {
        setTranslateScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .assertExists("No answer icon")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_verticalScreen_answerTextIsDisplayed() {
        setTranslateScreen(isRuToEn = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .performClick()
        composeTestRule.onNodeWithText(FakeSource.fakeSentencesUi[0].enText)
            .assertExists()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_horizontalScreen_answerTextIsDisplayed() {
        setTranslateScreen(isVerticalScreen = false, isRuToEn = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .performClick()
        composeTestRule.onNodeWithText(FakeSource.fakeSentencesUi[0].enText)
            .assertExists()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_verticalScreen_nextButtonIsDisplayed() {
        setTranslateScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertExists("No next button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_horizontalScreen_nextButtonIsDisplayed() {
        setTranslateScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertExists("No next button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_verticalScreen_nextButtonSizeIsRelevant() {
        setTranslateScreen()
        composeTestRule.onNodeWithTagById(R.string.next_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun translateScreen_horizontalScreen_nextButtonSizeIsRelevant() {
        setTranslateScreen(isVerticalScreen = false)
        composeTestRule.onNodeWithTagById(R.string.next_button)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun translateScreen_verticalScreen_errorTextIsDisplayed() {
        setTranslateScreen(isError = true)
        composeTestRule.onNodeWithTextById(R.string.something_happened)
            .assertExists("No error text")
            .assertIsDisplayed()
    }

    @Test
    fun translateScreen_horizontalScreen_errorTextIsDisplayed() {
        setTranslateScreen(isVerticalScreen = false, isError = true)
        composeTestRule.onNodeWithTextById(R.string.something_happened)
            .assertExists("No error text")
            .assertIsDisplayed()
    }

    @Test
    fun translateScreen_verticalScreen_errorButtonIsDisplayed() {
        setTranslateScreen(isError = true)
        composeTestRule.onNodeWithTextById(R.string.try_again)
            .assertExists("No error button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun translateScreen_horizontalScreen_errorButtonIsDisplayed() {
        setTranslateScreen(isVerticalScreen = false, isError = true)
        composeTestRule.onNodeWithTextById(R.string.try_again)
            .assertExists("No error button")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun setTranslateScreen(
        isVerticalScreen: Boolean = true,
        isError: Boolean = false,
        score: Int = 0,
        isRuToEn: Boolean = true,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                TranslateScreen(
                    modifier = Modifier.fillMaxSize(),
                    isError = isError,
                    onBackClick = {},
                    isVerticalScreen = isVerticalScreen,
                    sentence = FakeSource.fakeSentencesUi[0],
                    score = score,
                    isRuToEn = isRuToEn,
                    onNextClick = {},
                    onErrorButtonClick = {},
                )
            }
        }
    }
}
