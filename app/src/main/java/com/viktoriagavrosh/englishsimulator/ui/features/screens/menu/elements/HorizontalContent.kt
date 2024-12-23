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
 * Composable to display buttons (horizontal screen orientation)
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun HorizontalContent(
    buttonItems: List<MenuButtonItem>,
    isScreenWithButtons: Boolean,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = if (isScreenWithButtons) {
            GridCells.Fixed(2)
        } else {
            GridCells.Adaptive(minSize = dimensionResource(R.dimen.menu_small_button_width))
        },
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier,
    ) {
        if (isScreenWithButtons) {
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
        } else {
            items(buttonItems) { item ->
                QuestButton(
                    onClick = item.onClick,
                    text = item.title,
                    modifier = Modifier
                    //.fillMaxWidth()
                    //.padding(bottom = dimensionResource(R.dimen.padding_medium)),
                )
            }
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
            modifier = Modifier.fillMaxSize()
        )
    }
}
