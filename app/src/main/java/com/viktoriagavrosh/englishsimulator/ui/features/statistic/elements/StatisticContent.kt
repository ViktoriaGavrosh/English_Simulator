package com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display statistic
 *
 * @param translateScoresProvider provides items for ui
 * @param issueScoresProvider provides items for ui
 * @param dialogScoresProvider provides items for ui
 * @param wordScoresProvider provides items for ui
 * @param onTabClick callback that is executed when tab is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun StatisticContent(
    translateScoresProvider: () -> Map<Int, Int>,
    issueScoresProvider: () -> Map<Int, Int>,
    dialogScoresProvider: () -> Map<Int, Int>,
    wordScoresProvider: () -> Map<Int, Int>,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.e("123", "StatisticContent")      // TODO log

    val tabs = listOf(stringResource(R.string.last_month), stringResource(R.string.this_month))
    var selectedTabIndex by remember { mutableIntStateOf(1) }
    Column(
        modifier = modifier
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, month ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        onTabClick(index)
                    },
                    modifier = Modifier
                        .heightIn(min = dimensionResource(R.dimen.button_min_height))
                        .testTag(month)
                ) {
                    Text(
                        text = month,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(vertical = dimensionResource(R.dimen.padding_double_small))
                    )
                }
            }
        }
        StatisticColumn(
            translateScoresProvider = translateScoresProvider,
            issueScoresProvider = issueScoresProvider,
            dialogScoresProvider = dialogScoresProvider,
            wordScoresProvider = wordScoresProvider,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_double_small))
                .padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview
@Composable
private fun StatisticContentPreview() {
    val scores = mapOf(1 to 5, 2 to 35, 5 to 32, 7 to 12, 9 to 35)
    EnglishSimulatorTheme {
        StatisticContent(
            translateScoresProvider = { scores },
            issueScoresProvider = { scores },
            dialogScoresProvider = { scores },
            wordScoresProvider = { scores },
            onTabClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
