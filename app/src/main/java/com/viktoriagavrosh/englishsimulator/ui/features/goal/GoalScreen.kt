package com.viktoriagavrosh.englishsimulator.ui.features.goal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.model.Goal
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.goal.elements.GoalContent
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.IconRow
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * Composable to display daily goal
 *
 * @param date date of current day
 * @param onBackClick callback that is executed when back button is clicked
 * @param onEditGoalsClick callback that is executed when edit button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun GoalScreen(
    date: String,
    onBackClick: () -> Unit,
    onEditGoalsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: GoalViewModel = koinViewModel {
        parametersOf(date)
    }
    val dayStatisticState by viewModel.dayStatisticState.collectAsStateWithLifecycle()
    val goal by viewModel.goalState.collectAsStateWithLifecycle()

    if (dayStatisticState is RequestResult.Error) {
        ErrorScreen(
            onErrorButtonClick = onBackClick,
            modifier = modifier
        )
    } else {
        GoalScreen(
            dayStatisticProvider = { dayStatisticState.data?.first() ?: Statistic(date = date) },
            goal = goal,
            onEditGoalsClick = onEditGoalsClick,
            modifier = modifier,
        )
    }
}

/**
 * Composable to display daily goal
 *
 * @param dayStatisticProvider provides scores of current day
 * @param goal daily goals for all quests
 * @param onEditGoalsClick callback that is executed when edit button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun GoalScreen(
    dayStatisticProvider: () -> Statistic,
    goal: Goal,
    onEditGoalsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        GoalContent(
            dayStatisticProvider = dayStatisticProvider,
            goal = goal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.padding_extra_large)
                )
        )
        IconRow(
            iconId = R.drawable.ic_edit,
            contentDescription = stringResource(R.string.edit),
            onIconClick = onEditGoalsClick,
            isLeft = false,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@VerticalScreenPreview
@Composable
private fun GoalScreenPreview() {
    EnglishSimulatorTheme {
        GoalScreen(
            dayStatisticProvider = {
                Statistic(
                    translateScore = 5,
                    issueScore = 7,
                    dialogScore = 9,
                    wordScore = 12,
                )
            },
            goal = Goal(
                translateGoal = 15,
                issueGoal = 3,
                dialogGoal = 9,
                wordGoal = 87,
            ),
            onEditGoalsClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
