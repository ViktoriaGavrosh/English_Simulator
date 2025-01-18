package com.viktoriagavrosh.englishsimulator.ui.features.screens.menu

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.elements.HorizontalContent
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.elements.SelectionDropdownMenu
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.elements.VerticalContent
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display menu of quest "Translate sentences"
 *
 * @param title text for cover
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param isScreenWithButtons if true buttons will show
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param dropdownMenuSelectedOptionProvider provides selected item for dropdownMenu
 * @param dropdownMenuOptions list of items to select for dropdownMenu
 * @param onDropdownMenuValueChange callback that is executed when item of dropdownMenu is selected
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun MenuScreenContent(
    title: String,
    isVerticalScreen: Boolean,
    isScreenWithButtons: Boolean,
    buttonItems: List<MenuButtonItem>,
    dropdownMenuSelectedOptionProvider: () -> String,
    dropdownMenuOptions: List<String>,
    onDropdownMenuValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = if (isVerticalScreen) {
                Modifier
                    .padding(
                        top = dimensionResource(R.dimen.padding_double_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            } else {
                Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_double_small))
            }
        )
        if (dropdownMenuOptions.isNotEmpty()) {
            SelectionDropdownMenu(
                options = dropdownMenuOptions,
                selectedOptionProvider = dropdownMenuSelectedOptionProvider,
                onValueChange = onDropdownMenuValueChange,
                modifier = Modifier.testTag(stringResource(R.string.dropdown_menu_tag))
            )
        }
        if (isVerticalScreen) {
            VerticalContent(
                buttonItems = buttonItems,
                isScreenWithButtons = isScreenWithButtons,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium)
                    )
                    .testTag(stringResource(R.string.vertical_menu_content_tag)),
            )
        } else {
            HorizontalContent(
                buttonItems = buttonItems,
                isScreenWithButtons = isScreenWithButtons,
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .testTag(stringResource(R.string.horizontal_menu_content_tag)),
            )
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalMenuScreenContentPreview() {
    EnglishSimulatorTheme {
        MenuScreenContent(
            title = "Title of the game",
            isVerticalScreen = true,
            buttonItems = listOf(
                MenuButtonItem(title = "Button 1 with large text"),
                MenuButtonItem(title = "Button 2"),
            ),
            isScreenWithButtons = true,
            dropdownMenuOptions = emptyList(),
            dropdownMenuSelectedOptionProvider = { "" },
            onDropdownMenuValueChange = {},
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
        MenuScreenContent(
            title = "Title of the game",
            isVerticalScreen = false,
            buttonItems = List(2) {
                MenuButtonItem(title = "Button $it")
            },
            isScreenWithButtons = true,
            dropdownMenuOptions = emptyList(),
            dropdownMenuSelectedOptionProvider = { "" },
            onDropdownMenuValueChange = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalFullMenuScreenContentPreview() {
    EnglishSimulatorTheme {
        MenuScreenContent(
            title = "Title of the game",
            isVerticalScreen = true,
            buttonItems = List(8) {
                MenuButtonItem(title = "Button $it")
            },
            isScreenWithButtons = false,
            dropdownMenuOptions = listOf("first", "second"),
            dropdownMenuSelectedOptionProvider = { "first" },
            onDropdownMenuValueChange = {},
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
private fun HorizontalFullMenuScreenContentPreview() {
    EnglishSimulatorTheme {
        MenuScreenContent(
            title = "Title of the game",
            isVerticalScreen = false,
            buttonItems = List(8) {
                MenuButtonItem(title = "Button $it")
            },
            isScreenWithButtons = false,
            dropdownMenuOptions = listOf("first", "second"),
            dropdownMenuSelectedOptionProvider = { "first" },
            onDropdownMenuValueChange = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalCardMenuScreenContentPreview() {
    EnglishSimulatorTheme {
        MenuScreenContent(
            title = "Title of the game",
            isVerticalScreen = true,
            buttonItems = List(8) {
                MenuButtonItem(title = "Button $it")
            },
            isScreenWithButtons = false,
            dropdownMenuOptions = emptyList(),
            dropdownMenuSelectedOptionProvider = { "" },
            onDropdownMenuValueChange = {},
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
private fun HorizontalCardMenuScreenContentPreview() {
    EnglishSimulatorTheme {
        MenuScreenContent(
            title = "Title of the game",
            isVerticalScreen = false,
            buttonItems = List(8) {
                MenuButtonItem(title = "Button $it")
            },
            isScreenWithButtons = false,
            dropdownMenuOptions = emptyList(),
            dropdownMenuSelectedOptionProvider = { "" },
            onDropdownMenuValueChange = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
