package com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.model.MenuButtonItem

/**
 * Composable to display buttons (vertical screen orientation)
 *
 * @param buttonItems list of [MenuButtonItem] for buttons
 * @param isScreenWithButtons if true buttons will show
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun VerticalContent(
    buttonItems: List<MenuButtonItem>,
    isScreenWithButtons: Boolean,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            if (isScreenWithButtons) {
                dimensionResource(R.dimen.padding_extra_large)
            } else {
                dimensionResource(R.dimen.padding_large)
            }
        ),
        modifier = modifier.padding(bottom = dimensionResource(R.dimen.padding_large)),
    ) {
        items(
            items = buttonItems,
        ) { item ->
            if (isScreenWithButtons) {
                QuestButton(
                    onClick = item.onClick,
                    text = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.padding_double_extra_large)),
                )
            } else {
                QuestCard(
                    onClick = item.onClick,
                    text = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}
