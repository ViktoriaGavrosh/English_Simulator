package com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.model.MenuButtonItem
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
        modifier = modifier,
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
                    .padding(horizontal = dimensionResource(R.dimen.padding_super_extra_large))
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
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            for (subList in chunkedButtonItems) {
                ButtonColumn(
                    buttonItems = subList,
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
        val middleIndex = if (buttonItems.size % 2 == 0) {
            (buttonItems.size / 2) - 1
        } else {
            buttonItems.size / 2
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            ButtonColumn(
                buttonItems = buttonItems.subList(0, middleIndex),
                isLargeButtons = false,
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
            ButtonColumn(
                buttonItems = buttonItems.subList(middleIndex + 1, buttonItems.size),
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
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = if (isLargeText) {
                MaterialTheme.typography.titleLarge
            } else {
                MaterialTheme.typography.titleMedium
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
            buttonItems = List(2) {
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
private fun HorizontalMenuScreenContentPreview() {
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
