package com.viktoriagavrosh.englishsimulator.ui.game

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.GameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.GameQuestion
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import org.junit.Rule
import org.junit.Test

class HorizontalGameScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun gameScreen_horizontal_backButtonIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button on GameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun gameScreen_horizontal_backButtonHasClickAction() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertHasClickAction()
    }

    @Test
    fun gameScreen_horizontal_backButtonSizeIsRelevant() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun gameScreen_horizontal_editButtonIsDisplayed() {
        setGameScreen(isEditButtonShow = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertExists("No edit button on GameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun gameScreen_horizontal_editButtonIsNotDisplayed() {
        setGameScreen(isEditButtonShow = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertIsNotDisplayed()
    }

    @Test
    fun gameScreen_horizontal_editButtonHasClickAction() {
        setGameScreen(isEditButtonShow = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertHasClickAction()
    }

    @Test
    fun gameScreen_horizontal_editButtonSizeIsRelevant() {
        setGameScreen(isEditButtonShow = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun gameScreen_horizontal_scoreBoxIsDisplayed() {
        val score = 54
        setGameScreen(score = score)
        composeTestRule.onNodeWithText(score.toString())
            .assertExists("No scoreBox on GameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun gameScreen_horizontal_scoreBoxHasNoClickAction() {
        val score = 54
        setGameScreen(score = score)
        composeTestRule.onNodeWithText(score.toString())
            .assertHasNoClickAction()
    }

    @Test
    fun gameScreen_horizontal_questionTextIsDisplayed() {
        val gameQuestion = FakeSource.fakeGameQuestion[0]
        setGameScreen(gameQuestion = gameQuestion)
        composeTestRule.onNodeWithText(gameQuestion.question)
            .assertExists("No question text")
            .assertIsDisplayed()
    }

    @Test
    fun gameScreen_horizontal_questionTextHasNoClickAction() {
        val gameQuestion = FakeSource.fakeGameQuestion[0]
        setGameScreen(gameQuestion = gameQuestion)
        composeTestRule.onNodeWithText(gameQuestion.question)
            .assertHasNoClickAction()
    }

    @Test
    fun gameScreen_horizontal_cardForTranslateTextIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .assertExists("No question mark on translate card")
            .assertIsDisplayed()
    }

    @Test
    fun gameScreen_horizontal_cardForTranslateTextHasClickAction() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .assertHasClickAction()
    }

    @Test
    fun gameScreen_horizontal_translateTextIsDisplayed() {
        val gameQuestion = FakeSource.fakeGameQuestion[0]
        setGameScreen(gameQuestion = gameQuestion)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .performClick()
        composeTestRule.onNodeWithText(gameQuestion.translate)
            .assertExists("No translate text")
            .assertIsDisplayed()
    }

    @Test
    fun gameScreen_horizontal_nextButtonIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertExists("No next button on GameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun gameScreen_horizontal_nextButtonHasClickAction() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertHasClickAction()
    }

    @Test
    fun gameScreen_horizontal_nextButtonSizeIsRelevant() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setGameScreen(
        gameQuestion: GameQuestion = FakeSource.fakeGameQuestion[0],
        score: Int = 0,
        isEditButtonShow: Boolean = false,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                GameScreen(
                    gameQuestionProvider = { gameQuestion },
                    scoreProvider = { score },
                    isVerticalScreen = false,
                    onBackClick = {},
                    onNextClick = {},
                    modifier = Modifier.fillMaxSize(),
                    isEditButtonShow = isEditButtonShow,
                    onEditButtonClick = {},
                )
            }
        }
    }
}
