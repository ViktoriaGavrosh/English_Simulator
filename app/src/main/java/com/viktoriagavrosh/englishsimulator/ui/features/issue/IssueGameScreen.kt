package com.viktoriagavrosh.englishsimulator.ui.features.issue

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.GameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.elements.ErrorScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun IssueGameScreen(
    isVerticalScreen: Boolean,
    theme: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: IssueGameViewModel = koinViewModel()
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
