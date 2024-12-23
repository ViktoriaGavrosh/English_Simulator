package com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display button with text
 *
 * @param onClick callback that is executed when button is clicked
 * @param text the text to be displayed
 * @param modifier the modifier to be applied to this layout node
 * @param isLargeText if true - button has large text on it
 */
@Composable
internal fun QuestButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    isLargeText: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.sizeIn(minHeight = dimensionResource(R.dimen.button_min_height))
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            style = if (isLargeText) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.titleSmall
            },
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_small))
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SmallQuestButtonPreview() {
    EnglishSimulatorTheme {
        QuestButton(
            onClick = {},
            text = "Text",
            isLargeText = false,
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LargeQuestButtonPreview() {
    EnglishSimulatorTheme {
        QuestButton(
            onClick = {},
            text = "Text on the button",
            isLargeText = true,
        )
    }
}
