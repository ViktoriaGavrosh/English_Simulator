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
import com.viktoriagavrosh.englishsimulator.ui.features.goal.elements.UpdateGoalContent
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.IconRow
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview
import org.koin.androidx.compose.koinViewModel

/**
 * Composable to update daily goal
 *
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun UpdateGoalScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: UpdateGoalViewModel = koinViewModel()

    val goalsUiState by viewModel.goalsUiState.collectAsStateWithLifecycle()

    UpdateGoalScreen(
        goal = goalsUiState,
        onTranslateGoalUpdate = viewModel::updateTranslateGoal,
        onIssueGoalUpdate = viewModel::updateIssueGoal,
        onDialogGoalUpdate = viewModel::updateDialogGoal,
        onWordGoalUpdate = viewModel::updateWordGoal,
        onBackClick = onBackClick,
        modifier = modifier
    )
}

/**
 * Composable to update daily goal
 *
 * @param goal daily goals of all quests
 * @param onTranslateGoalUpdate callback that is executed when translate goal is updated
 * @param onIssueGoalUpdate callback that is executed when issue goal is updated
 * @param onDialogGoalUpdate callback that is executed when dialog goal is updated
 * @param onWordGoalUpdate callback that is executed when word goal is updated
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun UpdateGoalScreen(
    goal: Goal,
    onTranslateGoalUpdate: (String) -> Unit,
    onIssueGoalUpdate: (String) -> Unit,
    onDialogGoalUpdate: (String) -> Unit,
    onWordGoalUpdate: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        UpdateGoalContent(
            goal = goal,
            onTranslateGoalUpdate = onTranslateGoalUpdate,
            onIssueGoalUpdate = onIssueGoalUpdate,
            onDialogGoalUpdate = onDialogGoalUpdate,
            onWordGoalUpdate = onWordGoalUpdate,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_double_medium))
        )
        IconRow(
            iconId = R.drawable.ic_back,
            contentDescription = stringResource(R.string.back),
            onIconClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@VerticalScreenPreview
@Composable
private fun UpdateGoalScreenPreview() {
    EnglishSimulatorTheme {
        UpdateGoalScreen(
            goal = Goal(
                translateGoal = 15,
                issueGoal = 3,
                dialogGoal = 9,
                wordGoal = 87,
            ),
            onTranslateGoalUpdate = {},
            onIssueGoalUpdate = {},
            onDialogGoalUpdate = {},
            onWordGoalUpdate = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
