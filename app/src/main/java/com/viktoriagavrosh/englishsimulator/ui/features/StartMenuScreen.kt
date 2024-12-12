package com.viktoriagavrosh.englishsimulator.ui.features

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display menu
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onTranslateButtonClick callback that is executed when translate button is clicked
 * @param onIssueButtonClick callback that is executed when issue button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun StartMenuScreen(
    isVerticalScreen: Boolean,
    onTranslateButtonClick: () -> Unit,
    onIssueButtonClick: () -> Unit,
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
        )
    )

    MenuScreen(
        buttonItems = buttonItems,
        title = stringResource(R.string.app_title),
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
            modifier = Modifier.fillMaxSize()
        )
    }
}
