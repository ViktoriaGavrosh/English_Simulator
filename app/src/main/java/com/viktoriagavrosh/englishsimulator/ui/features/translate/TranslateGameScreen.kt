package com.viktoriagavrosh.englishsimulator.ui.features.translate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.GameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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
    modifier: Modifier = Modifier,
) {
    val viewModel: TranslateViewModel = koinViewModel {
        parametersOf(quest == Quest.RuToEn)
    }
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isError) {
        ErrorScreen(
            onErrorButtonClick = viewModel::initUiState,
            modifier = modifier
        )
    } else {
        GameScreen(
            gameQuestion = uiState.gameQuestion,
            score = uiState.score,
            isVerticalScreen = isVerticalScreen,
            onBackClick = onBackClick,
            onNextClick = viewModel::updateUiState,
            modifier = modifier,
        )
    }
}
