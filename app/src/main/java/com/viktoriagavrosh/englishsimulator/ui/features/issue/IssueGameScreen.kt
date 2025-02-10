package com.viktoriagavrosh.englishsimulator.ui.features.issue

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.di.ISSUE_SCREEN
import com.viktoriagavrosh.englishsimulator.model.GameQuestionUi
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameViewModel
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

/**
 * Composable to display quest "Tell about yourself"
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param theme describes what action will be shown by Ui
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun IssueGameScreen(
    isVerticalScreen: Boolean,
    theme: String,
    onBackClick: () -> Unit,
    onIssueScoreUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: GameViewModel = koinViewModel(qualifier = named(ISSUE_SCREEN)) {
        parametersOf(theme)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    IssueGameScreen(
        gameQuestionProvider = { uiState.gameQuestion },
        scoreProvider = { uiState.score },
        isVerticalScreen = isVerticalScreen,
        isErrorProvider = { uiState.isError },
        onNextButtonClick = viewModel::updateUiState,
        onBackClick = {
            onIssueScoreUpdate(uiState.score)
            onBackClick()
        },
        modifier = modifier,
    )
}

/**
 * Composable to display quest "Tell about yourself"
 *
 * @param gameQuestionProvider provides item for ui
 * @param scoreProvider provides score of game
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param isErrorProvider provides boolean value of ScreenState
 * @param onNextButtonClick callback that is executed when next button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun IssueGameScreen(
    gameQuestionProvider: () -> GameQuestionUi,
    scoreProvider: () -> Int,
    isVerticalScreen: Boolean,
    isErrorProvider: () -> Boolean,
    onNextButtonClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isErrorProvider()) {
        ErrorScreen(
            onErrorButtonClick = onBackClick,
            modifier = modifier
        )
    } else {
        GameScreen(
            gameQuestionProvider = gameQuestionProvider,
            scoreProvider = scoreProvider,
            isVerticalScreen = isVerticalScreen,
            onBackClick = onBackClick,
            onNextClick = onNextButtonClick,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalIssueGameScreenPreview() {
    EnglishSimulatorTheme {
        IssueGameScreen(
            gameQuestionProvider = {
                GameQuestionUi(
                    question = "Question Question Question Question Question Question " +
                            "Question Question Question Question Question Question Question " +
                            "Question Question Question Question  Question  Question  Question",
                    translate = "Translate",
                )
            },
            scoreProvider = { 5 },
            isVerticalScreen = true,
            isErrorProvider = { false },
            onNextButtonClick = {},
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 1000, name = "Light")
@Preview(
    showBackground = true,
    widthDp = 1000,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HorizontalIssueGameScreenPreview() {
    EnglishSimulatorTheme {
        IssueGameScreen(
            gameQuestionProvider = {
                GameQuestionUi(
                    question = "Question",
                    translate = "Translate",
                )
            },
            scoreProvider = { 5 },
            isVerticalScreen = false,
            isErrorProvider = { false },
            onNextButtonClick = {},
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorVerticalIssueGameScreenPreview() {
    EnglishSimulatorTheme {
        IssueGameScreen(
            gameQuestionProvider = { GameQuestionUi() },
            scoreProvider = { 5 },
            isVerticalScreen = true,
            isErrorProvider = { true },
            onNextButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(showBackground = true, widthDp = 1000, name = "Light")
@Preview(
    showBackground = true,
    widthDp = 1000,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ErrorHorizontalIssueGameScreenPreview() {
    EnglishSimulatorTheme {
        IssueGameScreen(
            gameQuestionProvider = { GameQuestionUi() },
            scoreProvider = { 5 },
            isVerticalScreen = false,
            isErrorProvider = { true },
            onNextButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
