package com.viktoriagavrosh.englishsimulator.ui.screens.translate.elements

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display box with text or question mark
 *
 * @param text the text to be displayed
 * @param modifier the modifier to be applied to this layout node
 * @param isTextShow whether text should be shown. If false, question mark will be shown
 */
@Composable
internal fun TextBox(
    text: String,
    modifier: Modifier = Modifier,
    isTextShow: Boolean = true,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_shapes)))
            .aspectRatio(1.5F)
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center,
    ) {
        if (isTextShow) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.ic_question),
                contentDescription = stringResource(R.string.answer),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
            )
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextBoxPreview() {
    EnglishSimulatorTheme {
        TextBox(
            text = "Text",
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AnswerTextBoxPreview() {
    EnglishSimulatorTheme {
        TextBox(
            text = "Text",
            isTextShow = false,
        )
    }
}
