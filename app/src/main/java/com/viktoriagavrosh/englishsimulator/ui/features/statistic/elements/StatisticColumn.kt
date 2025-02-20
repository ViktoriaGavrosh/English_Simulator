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

/**
 * Composable to display statistics
 *
 * @param translateScoresProvider provides items for ui
 * @param issueScoresProvider provides items for ui
 * @param dialogScoresProvider provides items for ui
 * @param wordScoresProvider provides items for ui
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun StatisticColumn(
    translateScoresProvider: () -> List<Int>,
    issueScoresProvider: () -> List<Int>,
    dialogScoresProvider: () -> List<Int>,
    wordScoresProvider: () -> List<Int>,
    modifier: Modifier = Modifier
) {
    val quests = listOf(
        R.string.translate_button_title to translateScoresProvider,
        R.string.issue_button_title to issueScoresProvider,
        R.string.dialog_button_title to dialogScoresProvider,
        R.string.word_button_title to wordScoresProvider
    )

    Column(
        modifier = modifier
            .testTag(stringResource(R.string.statistic_column_tag))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_medium))
    ) {
        quests.forEach { quest ->
            QuestStatistic(
                title = stringResource(quest.first),
                scoresProvider = quest.second,
                modifier = Modifier.testTag(stringResource(quest.first))
            )
        }
    }
}

@Composable
private fun QuestStatistic(
    title: String,
    scoresProvider: () -> List<Int>,
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
            modifier = Modifier
                .height(210.dp)
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}
