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
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun MenuScreenContent(
    title: String,
    isVerticalScreen: Boolean,
    isScreenWithButtons: Boolean,
    isDropdownMenuShow: Boolean,
    buttonItems: List<MenuButtonItem>,
    onDropdownMenuValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val options = listOf(
        stringResource(R.string.short_ru_to_en),
        stringResource(R.string.short_en_to_ru)
    )
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
                    .padding(top = dimensionResource(R.dimen.padding_extra_large))
            } else {
                Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_large))
            }
        )
        if (isDropdownMenuShow) {
            SelectionDropdownMenu(
                options = options,
                selectedOption = options[0],
                onValueChange = onDropdownMenuValueChange,
            )
        }
        if (isVerticalScreen) {
            VerticalContent(
                buttonItems = buttonItems,
                isScreenWithButtons = isScreenWithButtons,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_large)
                )
            )
        } else {
            HorizontalContent(
                buttonItems = buttonItems,
                isScreenWithButtons = isScreenWithButtons,
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(dimensionResource(R.dimen.padding_large)),
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
            isDropdownMenuShow = false,
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
            isDropdownMenuShow = false,
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
            isDropdownMenuShow = true,
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
            isDropdownMenuShow = true,
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
            isDropdownMenuShow = false,
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
            isDropdownMenuShow = false,
            onDropdownMenuValueChange = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
