package com.viktoriagavrosh.englishsimulator.ui.screens.game.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.HorizontalScreenPreview
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to show message about download problems to user
 *
 * @param onErrorButtonClick callback that is executed when button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun ErrorScreen(
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.something_happened),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
        Button(
            onClick = onErrorButtonClick,
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.padding_double_large)
            ),
        ) {
            Text(
                text = stringResource(R.string.back),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@VerticalScreenPreview
@HorizontalScreenPreview
@Composable
private fun VerticalErrorScreenPreview() {
    EnglishSimulatorTheme {
        ErrorScreen(
            onErrorButtonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
