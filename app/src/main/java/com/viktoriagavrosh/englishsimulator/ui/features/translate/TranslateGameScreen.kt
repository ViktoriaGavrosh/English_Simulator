package com.viktoriagavrosh.englishsimulator.ui.features.translate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.di.TRANSLATE_SCREEN
import com.viktoriagavrosh.englishsimulator.model.GameQuestionUi
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.game.GameViewModel
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.HorizontalScreenPreview
import com.viktoriagavrosh.englishsimulator.utils.IsTruePreviewParameterProvider
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

/**
 * Composable to display quest "Translate sentences"
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param quest Constant [Quest] describes what action will be shown by Ui
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun TranslateGameScreen(
    isVerticalScreen: Boolean,
    quest: Quest,
    onBackClick: () -> Unit,
    onTranslateScoreUpdate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: GameViewModel = koinViewModel(qualifier = named(TRANSLATE_SCREEN)) {
        parametersOf(quest == Quest.RuToEn)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TranslateGameScreen(
        gameQuestionProvider = { uiState.gameQuestion },
        scoreProvider = { uiState.score },
        isVerticalScreen = isVerticalScreen,
        isErrorProvider = { uiState.isError },
        onNextButtonClick = {
            onTranslateScoreUpdate()
            viewModel.updateUiState()
        },
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

/**
 * Composable to display quest "Translate sentences"
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
internal fun TranslateGameScreen(
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

@VerticalScreenPreview
@Composable
private fun VerticalTranslateGameScreenPreview(
    @PreviewParameter(IsTruePreviewParameterProvider::class) isError: Boolean
) {
    EnglishSimulatorTheme {
        TranslateGameScreen(
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
            isErrorProvider = { isError },
            onNextButtonClick = {},
            onBackClick = {},
        )
    }
}

@HorizontalScreenPreview
@Composable
private fun HorizontalTranslateGameScreenPreview(
    @PreviewParameter(IsTruePreviewParameterProvider::class) isError: Boolean
) {
    EnglishSimulatorTheme {
        TranslateGameScreen(
            gameQuestionProvider = {
                GameQuestionUi(
                    question = "Question",
                    translate = "Translate",
                )
            },
            scoreProvider = { 5 },
            isVerticalScreen = false,
            isErrorProvider = { isError },
            onNextButtonClick = {},
            onBackClick = {},
        )
    }
}
