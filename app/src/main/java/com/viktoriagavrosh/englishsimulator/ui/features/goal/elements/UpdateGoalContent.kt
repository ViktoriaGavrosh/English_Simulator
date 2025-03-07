package com.viktoriagavrosh.englishsimulator.ui.features.goal.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.model.Goal
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to update daily goal
 *
 * @param goal daily goals of all quests
 * @param onTranslateGoalUpdate callback that is executed when translate goal is updated
 * @param onIssueGoalUpdate callback that is executed when issue goal is updated
 * @param onDialogGoalUpdate callback that is executed when dialog goal is updated
 * @param onWordGoalUpdate callback that is executed when word goal is updated
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun UpdateGoalContent(
    goal: Goal,
    onTranslateGoalUpdate: (String) -> Unit,
    onIssueGoalUpdate: (String) -> Unit,
    onDialogGoalUpdate: (String) -> Unit,
    onWordGoalUpdate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_medium)),
        modifier = modifier
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = stringResource(R.string.daily_goal_title),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
        GoalRow(
            title = stringResource(R.string.translate_button_title),
            goalScore = goal.translateGoal,
            onGoalUpdate = onTranslateGoalUpdate,
            modifier = Modifier.fillMaxWidth()
        )
        GoalRow(
            title = stringResource(R.string.issue_button_title),
            goalScore = goal.issueGoal,
            onGoalUpdate = onIssueGoalUpdate,
            modifier = Modifier.fillMaxWidth()
        )
        GoalRow(
            title = stringResource(R.string.dialog_button_title),
            goalScore = goal.dialogGoal,
            onGoalUpdate = onDialogGoalUpdate,
            modifier = Modifier.fillMaxWidth()
        )
        GoalRow(
            title = stringResource(R.string.word_button_title),
            goalScore = goal.wordGoal,
            onGoalUpdate = onWordGoalUpdate,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Composable to display daily goal for one quest
 *
 * @param title name of quest
 * @param goalScore daily goal
 * @param onGoalUpdate callback that is executed when goal is updated
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun GoalRow(
    title: String,
    goalScore: Int,
    onGoalUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        UpdateGoalBox(
            score = goalScore,
            onGoalUpdate = onGoalUpdate
        )
    }
}

@Composable
private fun UpdateGoalBox(
    score: Int,
    onGoalUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(
                width = dimensionResource(R.dimen.countBox_border),
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(dimensionResource(R.dimen.corner_shapes))
            )
            .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_shapes)))
            .size(dimensionResource(R.dimen.countBox_size)),
        contentAlignment = Alignment.Center,
    ) {
        TextField(
            value = score.toString(),
            onValueChange = onGoalUpdate,
            textStyle = MaterialTheme.typography.titleLarge,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@VerticalScreenPreview
@Composable
private fun UpdateGoalContentPreview() {
    EnglishSimulatorTheme {
        UpdateGoalContent(
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
            modifier = Modifier.fillMaxSize()
        )
    }
}
