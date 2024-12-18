package com.viktoriagavrosh.englishsimulator.ui.features.screens.menu

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display menu of quest "Translate sentences"
 *
 * @param title text for cover
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun MenuScreenContent(
    title: String,
    isVerticalScreen: Boolean,
    buttonItems: List<MenuButtonItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
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
                    .padding(top = dimensionResource(R.dimen.padding_large))
            } else {
                Modifier
            }
        )
        if (isVerticalScreen) {
            VerticalButtons(
                buttonItems = buttonItems,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_double_extra_large)
                ),
            )
        } else {
            HorizontalButtons(
                buttonItems = buttonItems,
                modifier = Modifier,
            )
        }
    }
}

/**
 * Composable to display buttons (horizontal screen orientation)
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun HorizontalButtons(
    buttonItems: List<MenuButtonItem>,
    modifier: Modifier = Modifier,
) {
    if (buttonItems.size > 1) {
        val subListSize = (buttonItems.size + 2) / 3
        val chunkedButtonItems = buttonItems.chunked(subListSize)

        Row(
            modifier = modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            for (subList in chunkedButtonItems) {
                ButtonColumn(
                    buttonItems = subList,
                    isLargeButtons = buttonItems.size < 3,
                    isSmallSpase = true,
                )
            }
        }
    } else {
        ButtonColumn(
            buttonItems = buttonItems,
            modifier = modifier,
        )
    }
}

/**
 * Composable to display buttons (vertical screen orientation)
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun VerticalButtons(
    buttonItems: List<MenuButtonItem>,
    modifier: Modifier = Modifier,
) {
    if (buttonItems.size > 4) {
        val middleIndex = (buttonItems.size + 1) / 2

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            ButtonColumn(
                buttonItems = buttonItems.subList(0, middleIndex),
                isLargeButtons = false,
            )
            ButtonColumn(
                buttonItems = buttonItems.subList(middleIndex, buttonItems.size),
                isLargeButtons = false,
            )
        }
    } else {
        ButtonColumn(
            buttonItems = buttonItems,
            modifier = modifier,
        )
    }
}

/**
 * Composable to display column of buttons
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param modifier the modifier to be applied to this layout node
 * @param isLargeButtons if true - button has large size
 * @param isSmallSpase if true - small spase between buttons
 */
@Composable
private fun ButtonColumn(
    buttonItems: List<MenuButtonItem>,
    modifier: Modifier = Modifier,
    isLargeButtons: Boolean = true,
    isSmallSpase: Boolean = false,
) {
    Column(
        modifier = if (isLargeButtons) {
            modifier.width(dimensionResource(R.dimen.menu_large_button_width))
        } else {
            modifier.width(dimensionResource(R.dimen.menu_small_button_width))
        },
        verticalArrangement = Arrangement.Center,
    ) {

        for (item in buttonItems) {
            QuestButton(
                onClick = item.onClick,
                text = item.title,
                isLargeText = isLargeButtons,
                modifier = if (isSmallSpase) {
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimensionResource(R.dimen.padding_medium)
                        )
                } else {
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimensionResource(R.dimen.padding_extra_large)
                        )
                }

            )
        }
    }
}

/**
 * Composable to display button with text
 *
 * @param onClick callback that is executed when button is clicked
 * @param text the text to be displayed
 * @param modifier the modifier to be applied to this layout node
 * @param isLargeText if true - button has large text on it
 */
@Composable
private fun QuestButton(
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
private fun VerticalMenuScreenContentPreview() {
    EnglishSimulatorTheme {
        MenuScreenContent(
            title = "Title of the game",
            isVerticalScreen = true,
            buttonItems = listOf(
                MenuButtonItem(title = "Button 1 with large text"),
                MenuButtonItem(title = "Button 2"),
            ),
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
            modifier = Modifier.fillMaxSize()
        )
    }
}
