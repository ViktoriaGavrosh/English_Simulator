package com.viktoriagavrosh.englishsimulator.ui.vertical.game.wordgame

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.model.GameQuestionUi
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordGameScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import org.junit.Rule
import org.junit.Test

class VerticalWordGameScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun wordGameScreen_vertical_backButtonIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button on WordGameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordGameScreen_vertical_editButtonIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertExists("No edit button on WordGameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordGameScreen_vertical_scoreBoxIsDisplayed() {
        val score = 54
        setGameScreen(score = score)
        composeTestRule.onNodeWithText(score.toString())
            .assertExists("No scoreBox on WordGameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun wordGameScreen_vertical_questionTextIsDisplayed() {
        val gameQuestion = FakeSource.fakeGameQuestion[0]
        setGameScreen(gameQuestion = gameQuestion)
        composeTestRule.onNodeWithText(gameQuestion.question)
            .assertExists("No question text")
            .assertIsDisplayed()
    }

    @Test
    fun wordGameScreen_vertical_cardForTranslateTextIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .assertExists("No question mark on translate card")
            .assertIsDisplayed()
    }

    @Test
    fun wordGameScreen_vertical_translateTextIsDisplayed() {
        val gameQuestion = FakeSource.fakeGameQuestion[0]
        setGameScreen(gameQuestion = gameQuestion)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .performClick()
        composeTestRule.onNodeWithText(gameQuestion.translate)
            .assertExists("No translate text")
            .assertIsDisplayed()
    }

    @Test
    fun wordGameScreen_vertical_nextButtonIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertExists("No next button on WordGameScreen")
            .assertIsDisplayed()
    }

    private fun setGameScreen(
        gameQuestion: GameQuestionUi = FakeSource.fakeGameQuestion[0],
        score: Int = 0,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                WordGameScreen(
                    gameQuestionProvider = { gameQuestion },
                    scoreProvider = { score },
                    isVerticalScreen = true,
                    isErrorProvider = { false },
                    onNextButtonClick = {},
                    onEditButtonClick = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
