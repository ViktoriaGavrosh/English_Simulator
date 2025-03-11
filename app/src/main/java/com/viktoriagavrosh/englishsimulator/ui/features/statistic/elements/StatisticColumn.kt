package com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.model.Goal
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to display statistics
 *
 * @param translateScoresProvider provides items for ui
 * @param issueScoresProvider provides items for ui
 * @param dialogScoresProvider provides items for ui
 * @param wordScoresProvider provides items for ui
 * @param goal daily goals of all quests
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun StatisticColumn(
    translateScoresProvider: () -> List<Int>,
    issueScoresProvider: () -> List<Int>,
    dialogScoresProvider: () -> List<Int>,
    wordScoresProvider: () -> List<Int>,
    goal: Goal,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .testTag(stringResource(R.string.statistic_column_tag))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_medium))
    ) {
        QuestStatistic(
            title = stringResource(R.string.translate_button_title),
            scoresProvider = translateScoresProvider,
            dailyGoal = goal.translateGoal,
            modifier = Modifier.testTag(stringResource(R.string.translate_button_title))
        )
        QuestStatistic(
            title = stringResource(R.string.issue_button_title),
            scoresProvider = issueScoresProvider,
            dailyGoal = goal.issueGoal,
            modifier = Modifier.testTag(stringResource(R.string.issue_button_title))
        )
        QuestStatistic(
            title = stringResource(R.string.dialog_button_title),
            scoresProvider = dialogScoresProvider,
            dailyGoal = goal.dialogGoal,
            modifier = Modifier.testTag(stringResource(R.string.dialog_button_title))
        )
        QuestStatistic(
            title = stringResource(R.string.word_button_title),
            scoresProvider = wordScoresProvider,
            dailyGoal = goal.wordGoal,
            modifier = Modifier.testTag(stringResource(R.string.word_button_title))
        )
    }
}

@Composable
private fun QuestStatistic(
    title: String,
    scoresProvider: () -> List<Int>,
    dailyGoal: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )
        ScoresRow(
            scores = scoresProvider(),
            dailyGoal = dailyGoal,
            modifier = Modifier
                .height(210.dp)
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@VerticalScreenPreview
@Composable
private fun StatisticColumnPreview() {
    val scores = listOf(23, 42, 67, 12)
    val goal = Goal(10, 10, 10, 10)
    EnglishSimulatorTheme {
        StatisticColumn(
            translateScoresProvider = { scores },
            issueScoresProvider = { scores },
            dialogScoresProvider = { scores },
            wordScoresProvider = { scores },
            goal = goal,
        )
    }
}
