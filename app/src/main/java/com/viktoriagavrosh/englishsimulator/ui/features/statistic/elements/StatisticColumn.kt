package com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.screens.game.ScoresRow

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
    translateScoresProvider: () -> Map<Int, Int>,
    issueScoresProvider: () -> Map<Int, Int>,
    dialogScoresProvider: () -> Map<Int, Int>,
    wordScoresProvider: () -> Map<Int, Int>,
    modifier: Modifier = Modifier
) {

    Log.e("123", "StatisticColumn")      // TODO log
    val quests = listOf(
        R.string.translate_button_title to translateScoresProvider,
        R.string.issue_button_title to issueScoresProvider,
        R.string.dialog_button_title to dialogScoresProvider,
        R.string.word_button_title to wordScoresProvider
    )

    LazyColumn(
        modifier = modifier.testTag(stringResource(R.string.statistic_column_tag)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_medium))
    ) {
        items(quests) {quest ->
            QuestStatistic(
                title = stringResource(quest.first) ,
                scoresProvider = quest.second,
                modifier = Modifier.testTag(stringResource( quest.first))
            )
        }
    }
}

@Composable
fun QuestStatistic(
    title: String,
    scoresProvider: () -> Map<Int, Int>,
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
                .heightIn(200.dp)
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}
