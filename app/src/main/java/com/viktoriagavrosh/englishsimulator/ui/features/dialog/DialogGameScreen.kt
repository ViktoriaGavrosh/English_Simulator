package com.viktoriagavrosh.englishsimulator.ui.features.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.GameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.GameQuestion
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import org.koin.androidx.compose.koinViewModel

/**
 * Composable to display quest "Short dialogs"
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun DialogGameScreen(
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: DialogGameViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    DialogGameScreen(
        gameQuestionProvider = { uiState.gameQuestion },
        scoreProvider = { uiState.score },
        isVerticalScreen = isVerticalScreen,
        isErrorProvider = { uiState.isError },
        onNextButtonClick = viewModel::updateUiState,
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

/**
 * Composable to display quest "Short dialogs"
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
internal fun DialogGameScreen(
    gameQuestionProvider: () -> GameQuestion,
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
private fun VerticalDialogGameScreenPreview() {
    EnglishSimulatorTheme {
        DialogGameScreen(
            gameQuestionProvider = {
                GameQuestion(
                    question = "Question Question Question Question Question Question " +
                            "Question Question Question Question Question Question Question " +
                            "Question Question Question Question",
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
private fun HorizontalDialogGameScreenPreview() {
    EnglishSimulatorTheme {
        DialogGameScreen(
            gameQuestionProvider = {
                GameQuestion(
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
private fun ErrorVerticalDialogGameScreenPreview() {
    EnglishSimulatorTheme {
        DialogGameScreen(
            gameQuestionProvider = { GameQuestion() },
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
private fun ErrorHorizontalDialogGameScreenPreview() {
    EnglishSimulatorTheme {
        DialogGameScreen(
            gameQuestionProvider = { GameQuestion() },
            scoreProvider = { 5 },
            isVerticalScreen = false,
            isErrorProvider = { true },
            onNextButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
