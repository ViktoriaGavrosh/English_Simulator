package com.viktoriagavrosh.englishsimulator.ui.features.goal.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to display daily goal of one quest
 *
 * @param title name of quest
 * @param goalScore daily goal for quest
 * @param questScore current score of quest
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun QuestGoal(
    title: String,
    goalScore: Int,
    questScore: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )
        GoalRow(
            goalScore = goalScore,
            currentScore = questScore,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun GoalRow(
    goalScore: Int,
    currentScore: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Slider(
            value = if (currentScore >= goalScore) goalScore.toFloat() else currentScore.toFloat(),
            onValueChange = {},
            valueRange = 0F..goalScore.toFloat(),
            modifier = Modifier
                .weight(1F)
                .testTag(stringResource(R.string.goal_slider_tag))
        )
        Text(
            text = goalScore.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_double_small))
        )
    }

}

@VerticalScreenPreview
@Composable
private fun QuestGoalPreview() {
    EnglishSimulatorTheme {
        QuestGoal(
            title = "Title",
            goalScore = 15,
            questScore = 10,
        )
    }
}
