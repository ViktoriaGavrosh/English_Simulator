package com.viktoriagavrosh.englishsimulator.ui.screens.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

@Composable
fun ComposeVerticalSlider(
    // TODO this is for StatisticScreen
    score: Int,
    isScoreShow: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .height((score * 2).dp)
            .width(40.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (score > 53) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.primaryContainer
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

@Composable
fun SlidersRow(
    scores: Map<String, Int>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        items(
            scores.keys.toList(),
            key = { it }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val score = scores[it] ?: 0
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
                        .padding(top = 4.dp, bottom = 4.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
@Preview
fun ComposeVerticalSliderPreview() {
    EnglishSimulatorTheme {
        SlidersRow(
            scores = mapOf(
                "12" to 46,
                "13" to 58,
                "14" to 31,
                "15" to 64,
                "16" to 10
            )
        )
    }
}
