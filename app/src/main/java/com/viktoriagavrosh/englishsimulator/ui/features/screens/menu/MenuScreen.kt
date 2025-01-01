package com.viktoriagavrosh.englishsimulator.ui.features.screens.menu

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.elements.IconRow
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display menu
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param title text for cover
 * @param isScreenWithButtons if true buttons will show
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 * @param isBackButtonShow if true back button shown on top of screen
 * @param dropdownMenuSelectedOptionProvider provides selected item for dropdownMenu
 * @param dropdownMenuOptions list of items to select for dropdownMenu
 * @param onDropdownMenuValueChange callback that is executed when item of dropdownMenu is selected
 */
@Composable
internal fun MenuScreen(
    buttonItems: List<MenuButtonItem>,
    title: String,
    isScreenWithButtons: Boolean,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    isBackButtonShow: Boolean = true,
    dropdownMenuSelectedOptionProvider: () -> String = { "" },
    dropdownMenuOptions: List<String> = emptyList(),
    onDropdownMenuValueChange: (String) -> Unit = {},
) {
    Box(
        modifier = modifier,
    ) {
        MenuScreenContent(
            title = title,
            isVerticalScreen = isVerticalScreen,
            isScreenWithButtons = isScreenWithButtons,
            buttonItems = buttonItems,
            dropdownMenuSelectedOptionProvider = dropdownMenuSelectedOptionProvider,
            dropdownMenuOptions = dropdownMenuOptions,
            onDropdownMenuValueChange = onDropdownMenuValueChange,
            modifier = Modifier.fillMaxSize()
        )
        if (isBackButtonShow) {
            IconRow(
                iconId = R.drawable.ic_back,
                contentDescription = stringResource(R.string.back),
                onIconClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(stringResource(R.string.back_button)),
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
            isScreenWithButtons = true,
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
            isScreenWithButtons = true,
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
            isScreenWithButtons = true,
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
            isScreenWithButtons = true,
            title = "Title of the game",
            isVerticalScreen = false,
            onBackClick = {},
        )
    }
}
