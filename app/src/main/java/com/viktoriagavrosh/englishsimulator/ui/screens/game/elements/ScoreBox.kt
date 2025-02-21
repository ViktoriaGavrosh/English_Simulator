package com.viktoriagavrosh.englishsimulator.ui.screens.game.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to display quest score
 *
 * @param score quest score
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun ScoreBox(
    score: Int,
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
            .size(dimensionResource(R.dimen.countBox_size))
            .background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@VerticalScreenPreview
@Composable
private fun CountBoxPreview() {
    EnglishSimulatorTheme {
        ScoreBox(
            score = 1,
        )
    }
}
