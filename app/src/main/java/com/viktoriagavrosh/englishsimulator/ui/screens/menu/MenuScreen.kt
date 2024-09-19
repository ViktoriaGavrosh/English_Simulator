package com.viktoriagavrosh.englishsimulator.ui.screens.menu

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display menu of quest "Translate sentences"
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onFirstButtonClick callback that is executed when first button is clicked
 * @param onSecondButtonClick callback that is executed when second button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun MenuScreen(
    isVerticalScreen: Boolean,
    onFirstButtonClick: (Quest) -> Unit,
    onSecondButtonClick: (Quest) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                bottom = dimensionResource(R.dimen.padding_super_extra_large)
            )
        )
        if (isVerticalScreen) {
            ButtonColumn(
                onTopButtonClick = onFirstButtonClick,
                onBottomButtonClick = onSecondButtonClick,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_extra_large)
                ),
            )
        } else {
            ButtonRow(
                onLeftButtonClick = onFirstButtonClick,
                onRightButtonClick = onSecondButtonClick,
                modifier = Modifier,
            )
        }
    }
}

/**
 * Composable to display buttons (vertical screen orientation)
 *
 * @param onTopButtonClick callback that is executed when top button is clicked
 * @param onBottomButtonClick callback that is executed when bottom button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun ButtonColumn(
    onTopButtonClick: (Quest) -> Unit,
    onBottomButtonClick: (Quest) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        QuestButton(
            onClick = onTopButtonClick,
            text = stringResource(R.string.ru_to_en),
            modifier = Modifier
        )
        QuestButton(
            onClick = onBottomButtonClick,
            text = stringResource(R.string.en_to_ru),
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.padding_extra_large)
            )
        )
    }
}

/**
 * Composable to display buttons (horizontal screen orientation)
 *
 * @param onLeftButtonClick callback that is executed when left button is clicked
 * @param onRightButtonClick callback that is executed when right button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun ButtonRow(
    onLeftButtonClick: (Quest) -> Unit,
    onRightButtonClick: (Quest) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuestButton(
            onClick = onLeftButtonClick,
            text = stringResource(R.string.ru_to_en),
            modifier = Modifier
        )
        QuestButton(
            onClick = onRightButtonClick,
            text = stringResource(R.string.en_to_ru),
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.padding_extra_large))
        )
    }
}

/**
 * Composable to display button with text
 *
 * @param onClick callback that is executed when button is clicked
 * @param text the text to be displayed
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun QuestButton(
    onClick: (Quest) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = { onClick(Quest.RuToEn) },
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalMenuScreenPreview() {
    EnglishSimulatorTheme {
        MenuScreen(
            isVerticalScreen = true,
            onFirstButtonClick = {},
            onSecondButtonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 1000)
@Preview(
    showBackground = true,
    name = "Dark",
    widthDp = 1000,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HorizontalMenuScreenPreview() {
    EnglishSimulatorTheme {
        MenuScreen(
            isVerticalScreen = false,
            onFirstButtonClick = {},
            onSecondButtonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
