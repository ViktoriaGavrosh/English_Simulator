package com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display content (horizontal screen orientation)
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param isScreenWithButtons if true buttons will show
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun HorizontalContent(
    buttonItems: List<MenuButtonItem>,
    isScreenWithButtons: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isScreenWithButtons) {
        ButtonGrid(
            buttonItems = buttonItems,
            modifier = modifier,
        )
    } else {
        CardGrid(
            buttonItems = buttonItems,
            modifier = modifier,
        )
    }
}

/**
 * Composable to display buttons (horizontal screen orientation)
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun ButtonGrid(
    buttonItems: List<MenuButtonItem>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_small)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_small)),
        modifier = modifier,
    ) {

        items(buttonItems) { item ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                QuestButton(
                    onClick = item.onClick,
                    text = item.title,
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                )
            }
        }
    }
}

/**
 * Composable to display cards (horizontal screen orientation)
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun CardGrid(
    buttonItems: List<MenuButtonItem>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensionResource(R.dimen.menu_large_button_width)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_small)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_small)),
        modifier = modifier,
    ) {

        items(buttonItems) { item ->
            QuestCard(
                onClick = item.onClick,
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
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
private fun HorizontalContentPreview() {
    EnglishSimulatorTheme {
        HorizontalContent(
            buttonItems = listOf(
                MenuButtonItem(title = "Button 1 with large text"),
                MenuButtonItem(title = "Button 2"),
                MenuButtonItem(title = "Button 3 with large text"),
                MenuButtonItem(title = "Button 4"),
            ),
            isScreenWithButtons = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
