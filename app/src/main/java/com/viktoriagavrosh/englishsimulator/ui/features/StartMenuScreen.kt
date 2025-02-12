package com.viktoriagavrosh.englishsimulator.ui.features

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display menu
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onTranslateButtonClick callback that is executed when translate button is clicked
 * @param onIssueButtonClick callback that is executed when issue button is clicked
 * @param onDialogButtonClick callback that is executed when dialog button is clicked
 * @param onWordButtonClick callback that is executed when word button is clicked
 * @param onStatisticButtonClick callback that is executed when statistic button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun StartMenuScreen(
    isVerticalScreen: Boolean,
    onTranslateButtonClick: () -> Unit,
    onIssueButtonClick: () -> Unit,
    onDialogButtonClick: () -> Unit,
    onWordButtonClick: () -> Unit,
    onStatisticButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val buttonItems = listOf(
        MenuButtonItem(
            title = stringResource(R.string.translate_button_title),
            onClick = onTranslateButtonClick,
        ),
        MenuButtonItem(
            title = stringResource(R.string.issue_button_title),
            onClick = onIssueButtonClick,
        ),
        MenuButtonItem(
            title = stringResource(R.string.dialog_button_title),
            onClick = onDialogButtonClick,
        ),
        MenuButtonItem(
            title = stringResource(R.string.word_button_title),
            onClick = onWordButtonClick,
        ),
        MenuButtonItem(
            title = stringResource(R.string.statistic_button_title),
            onClick = onStatisticButtonClick,
        )
    )

    MenuScreen(
        buttonItems = buttonItems,
        title = if (isVerticalScreen) {
            stringResource(R.string.app_vertical_title)
        } else {
            stringResource(R.string.app_horizontal_title)
        },
        isScreenWithButtons = true,
        isVerticalScreen = isVerticalScreen,
        onBackClick = {},
        modifier = modifier,
        isBackButtonShow = false,
    )
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalStartMenuScreenPreview() {
    EnglishSimulatorTheme {
        StartMenuScreen(
            isVerticalScreen = true,
            onTranslateButtonClick = {},
            onIssueButtonClick = {},
            onDialogButtonClick = {},
            onWordButtonClick = {},
            onStatisticButtonClick = {},
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
private fun HorizontalMenuScreenContentPreview() {
    EnglishSimulatorTheme {
        StartMenuScreen(
            isVerticalScreen = false,
            onTranslateButtonClick = {},
            onIssueButtonClick = {},
            onDialogButtonClick = {},
            onWordButtonClick = {},
            onStatisticButtonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
