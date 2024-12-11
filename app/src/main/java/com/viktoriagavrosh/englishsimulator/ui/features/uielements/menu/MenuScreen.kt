package com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.elements.BackRow
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

// TODO fix + comment
@Composable
internal fun MenuScreen(
    buttonItems: List<MenuButtonItem>,
    title: String,
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

@Preview
@Composable
private fun MenuScreenPreview() {
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
