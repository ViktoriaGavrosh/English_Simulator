package com.viktoriagavrosh.englishsimulator.ui.features.statistic

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.StatisticContent
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.HorizontalScreenPreview
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview
import org.koin.androidx.compose.koinViewModel

/**
 * Composable to display statistic
 *
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun StatisticScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: StatisticViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StatisticScreen(
        translateScoresProvider = { uiState.translateScores },
        issueScoresProvider = { uiState.issueScores },
        dialogScoresProvider = { uiState.dialogScores },
        wordScoresProvider = { uiState.wordScores },
        isErrorProvider = { uiState.isError },
        onTabClick = viewModel::updateUiState,
        onBackClick = onBackClick,
        modifier = modifier
    )
}

/**
 * Composable to display statistic
 *
 * @param translateScoresProvider provides items for ui
 * @param issueScoresProvider provides items for ui
 * @param dialogScoresProvider provides items for ui
 * @param wordScoresProvider provides items for ui
 * @param isErrorProvider provides boolean value of ScreenState
 * @param onTabClick callback that is executed when tab is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun StatisticScreen(
    translateScoresProvider: () -> Map<Int, Int>,
    issueScoresProvider: () -> Map<Int, Int>,
    dialogScoresProvider: () -> Map<Int, Int>,
    wordScoresProvider: () -> Map<Int, Int>,
    isErrorProvider: () -> Boolean,
    onTabClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Log.e("123", "StatisticScreen")      // TODO log
    if (isErrorProvider()) {
        ErrorScreen(
            onErrorButtonClick = onBackClick,
            modifier = modifier
        )
    } else {
        StatisticContent(
            translateScoresProvider = translateScoresProvider,
            issueScoresProvider = issueScoresProvider,
            dialogScoresProvider = dialogScoresProvider,
            wordScoresProvider = wordScoresProvider,
            onTabClick = onTabClick,
            modifier = modifier,
        )
    }
}

@VerticalScreenPreview
@HorizontalScreenPreview
@Composable
private fun VerticalStatisticScreenPreview() {
    val scores = mapOf(1 to 5, 2 to 35, 5 to 32, 7 to 12)
    EnglishSimulatorTheme {
        StatisticScreen(
            translateScoresProvider = { scores },
            issueScoresProvider = { scores },
            dialogScoresProvider = { scores },
            wordScoresProvider = { scores },
            isErrorProvider = { false },
            onTabClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
