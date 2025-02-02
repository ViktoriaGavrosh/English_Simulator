package com.viktoriagavrosh.englishsimulator.ui.horizontal.game.issuegame

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.model.GameQuestionUi
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueGameScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import org.junit.Rule
import org.junit.Test

class HorizontalIssueGameScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun issueGameScreen_horizontal_backButtonIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button on IssueGameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueGameScreen_horizontal_editButtonIsNotDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .assertIsNotDisplayed()
    }

    @Test
    fun issueGameScreen_horizontal_scoreBoxIsDisplayed() {
        val score = 54
        setGameScreen(score = score)
        composeTestRule.onNodeWithText(score.toString())
            .assertExists("No scoreBox on IssueGameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun issueGameScreen_horizontal_questionTextIsDisplayed() {
        val gameQuestion = FakeSource.fakeGameQuestion[0]
        setGameScreen(gameQuestion = gameQuestion)
        composeTestRule.onNodeWithText(gameQuestion.question)
            .assertExists("No question text")
            .assertIsDisplayed()
    }

    @Test
    fun issueGameScreen_horizontal_cardForTranslateTextIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .assertExists("No question mark on translate card")
            .assertIsDisplayed()
    }

    @Test
    fun issueGameScreen_horizontal_translateTextIsDisplayed() {
        val gameQuestion = FakeSource.fakeGameQuestion[0]
        setGameScreen(gameQuestion = gameQuestion)
        composeTestRule.onNodeWithContentDescriptionById(R.string.answer)
            .performClick()
        composeTestRule.onNodeWithText(gameQuestion.translate)
            .assertExists("No translate text")
            .assertIsDisplayed()
    }

    @Test
    fun issueGameScreen_horizontal_nextButtonIsDisplayed() {
        setGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.next_question)
            .assertExists("No next button on IssueGameScreen")
            .assertIsDisplayed()
    }

    private fun setGameScreen(
        gameQuestion: GameQuestionUi = FakeSource.fakeGameQuestion[0],
        score: Int = 0,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                IssueGameScreen(
                    gameQuestionProvider = { gameQuestion },
                    scoreProvider = { score },
                    isVerticalScreen = false,
                    isErrorProvider = { false },
                    onNextButtonClick = {},
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
