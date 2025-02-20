package com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to display all scores of month for one quest
 *
 * @param scores provides items for UI
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun ScoresRow(
    scores: List<Int>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.Bottom,
    ) {
        itemsIndexed(
            items = scores,
            key = { index, _ -> index }
        ) { index, item ->
            OneDayScore(
                score = item,
                day = index + 1
            )
        }
    }
}

@Composable
private fun OneDayScore(
    score: Int,
    day: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (score < 15) {
            Text(
                text = score.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.fillMaxWidth()
            )
        }
        ComposeVerticalSlider(
            score = score,
            isScoreShow = score >= 15,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .width(48.dp)
                .padding(top = 4.dp, bottom = 4.dp)
                .testTag(stringResource(R.string.day_horizontal_divider_tag)),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = day.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun ComposeVerticalSlider(
    score: Int,
    isScoreShow: Boolean,
    modifier: Modifier = Modifier,
) {
    val height = when {
        score < 0 -> 0
        score > 100 -> 200
        else -> score * 2
    }
    Card(
        modifier = modifier
            .height((height).dp)
            .width(40.dp)
            .testTag(stringResource(R.string.one_day_on_scores_row_tag)),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (score > 5) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onPrimary
            }
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary)
    ) {
        if (isScoreShow) {
            Text(
                text = score.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@VerticalScreenPreview
@Composable
private fun ComposeVerticalSliderPreview() {
    EnglishSimulatorTheme {
        ScoresRow(
            scores = listOf(24, 12, 15, 45, 23, 42)
        )
    }
}
