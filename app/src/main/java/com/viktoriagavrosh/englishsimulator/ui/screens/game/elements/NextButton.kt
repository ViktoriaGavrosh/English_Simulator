package com.viktoriagavrosh.englishsimulator.ui.screens.game.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to display button with icon
 *
 * @param onClick callback that is executed when button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun NextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .testTag(stringResource(R.string.next_button)),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_next),
            contentDescription = stringResource(R.string.next_question),
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_extra_large))
                .size(dimensionResource(R.dimen.icon_size))
        )
    }
}

@VerticalScreenPreview
@Composable
private fun NextButtonPreview() {
    EnglishSimulatorTheme {
        NextButton(
            onClick = {}
        )
    }
}
