package com.viktoriagavrosh.englishsimulator.ui.features.screens.menu

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.elements.BackRow
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display menu
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param title text for cover
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 * @param isBackButtonShow if true back button shown on top of screen
 */
@Composable
internal fun MenuScreen(
    buttonItems: List<MenuButtonItem>,
    title: String,
    isScreenWithButtons: Boolean = true,  // TODO fix it
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    isBackButtonShow: Boolean = true,
) {
    Box(
        modifier = modifier,
    ) {
        MenuScreenContent(
            title = title,
            isVerticalScreen = isVerticalScreen,
            isScreenWithButtons = isScreenWithButtons,
            buttonItems = buttonItems,
            modifier = Modifier.fillMaxSize()
        )
        if (isBackButtonShow) {
            BackRow(
                onBackClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalMenuScreenPreview() {
    EnglishSimulatorTheme {
        MenuScreen(
            buttonItems = List(2) {
                MenuButtonItem(title = "Button $it")
            },
            title = "Title of the game",
            isVerticalScreen = true,
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 1000, name = "Light")
@Preview(
    showBackground = true,
    widthDp = 1000,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HorizontalIssueMenuScreenPreview() {
    EnglishSimulatorTheme {
        MenuScreen(
            buttonItems = List(2) {
                MenuButtonItem(title = "Button $it")
            },
            title = "Title of the game",
            isVerticalScreen = false,
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalFullMenuScreenPreview() {
    EnglishSimulatorTheme {
        MenuScreen(
            buttonItems = List(8) {
                MenuButtonItem(title = "Button $it")
            },
            title = "Title of the game",
            isVerticalScreen = true,
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 1000, name = "Light")
@Preview(
    showBackground = true,
    widthDp = 1000,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HorizontalFullMenuScreenPreview() {
    EnglishSimulatorTheme {
        MenuScreen(
            buttonItems = List(8) {
                MenuButtonItem(title = "Button $it")
            },
            title = "Title of the game",
            isVerticalScreen = false,
            onBackClick = {},
        )
    }
}
