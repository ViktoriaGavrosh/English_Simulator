package com.viktoriagavrosh.englishsimulator.ui.features.word

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.di.WORD_SCREEN
import com.viktoriagavrosh.englishsimulator.model.GameQuestionUi
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameViewModel
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

/**
 * Composable to display quest "FlashCards"
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param quest Constant [Quest] describes what action will be shown by Ui
 * @param theme describes what action will be shown by Ui
 * @param onEditButtonClick callback that is executed when update button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun WordGameScreen(
    isVerticalScreen: Boolean,
    quest: Quest,
    theme: String,
    onEditButtonClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onWordScoreUpdate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isToEnglish = quest == Quest.RuToEn
    val viewModel: GameViewModel = koinViewModel(named(WORD_SCREEN)) {
        parametersOf(theme, isToEnglish)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WordGameScreen(
        gameQuestionProvider = { uiState.gameQuestion },
        scoreProvider = { uiState.score },
        isVerticalScreen = isVerticalScreen,
        isErrorProvider = { uiState.isError },
        onEditButtonClick = { onEditButtonClick(uiState.gameQuestion.id) },
        onNextButtonClick = {
            onWordScoreUpdate()
            viewModel.updateUiState()
        },
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

/**
 * Composable to display quest "FlashCards"
 *
 * @param gameQuestionProvider provides item for ui
 * @param scoreProvider provides score of game
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param isErrorProvider provides boolean value of ScreenState
 * @param onNextButtonClick callback that is executed when next button is clicked
 * @param onEditButtonClick callback that is executed when update button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun WordGameScreen(
    gameQuestionProvider: () -> GameQuestionUi,
    scoreProvider: () -> Int,
    isVerticalScreen: Boolean,
    isErrorProvider: () -> Boolean,
    onNextButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
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
            isEditButtonShow = true,
            onEditButtonClick = onEditButtonClick,
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalWordGameScreenPreview() {
    EnglishSimulatorTheme {
        WordGameScreen(
            gameQuestionProvider = {
                GameQuestionUi(
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
            onEditButtonClick = {},
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
private fun HorizontalWordGameScreenPreview() {
    EnglishSimulatorTheme {
        WordGameScreen(
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
            onEditButtonClick = {},
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorVerticalWordGameScreenPreview() {
    EnglishSimulatorTheme {
        WordGameScreen(
            gameQuestionProvider = { GameQuestionUi() },
            scoreProvider = { 5 },
            isVerticalScreen = true,
            isErrorProvider = { true },
            onNextButtonClick = {},
            onEditButtonClick = {},
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
private fun ErrorHorizontalWordGameScreenPreview() {
    EnglishSimulatorTheme {
        WordGameScreen(
            gameQuestionProvider = { GameQuestionUi() },
            scoreProvider = { 5 },
            isVerticalScreen = false,
            isErrorProvider = { true },
            onNextButtonClick = {},
            onEditButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
