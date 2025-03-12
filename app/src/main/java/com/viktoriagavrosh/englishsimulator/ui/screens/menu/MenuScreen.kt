package com.viktoriagavrosh.englishsimulator.ui.screens.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.IconRow
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.ButtonItemsPreviewParameterProvider
import com.viktoriagavrosh.englishsimulator.utils.HorizontalScreenPreview
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

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
 * @param isAddButtonShow if true add button shown on top of screen
 * @param onAddButtonClick callback that is executed when add button is clicked
 * @param isDailyGoalButtonShow if true daily goal button shown on top of screen
 * @param onDailyGoalButtonClick callback that is executed when daily goal button is clicked
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
    isAddButtonShow: Boolean = false,
    onAddButtonClick: () -> Unit = {},
    isDailyGoalButtonShow: Boolean = false,
    onDailyGoalButtonClick: () -> Unit = {},
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
                    .fillMaxWidth(),
            )
            if (isAddButtonShow) {
                IconRow(
                    iconId = R.drawable.ic_add,
                    contentDescription = stringResource(R.string.add),
                    onIconClick = onAddButtonClick,
                    isLeft = false,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        }
        if (isDailyGoalButtonShow) {
            IconRow(
                iconId = R.drawable.ic_daily_goal,
                contentDescription = stringResource(R.string.daily_goal),
                onIconClick = onDailyGoalButtonClick,
                isLeft = false,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}

@VerticalScreenPreview
@Composable
private fun VerticalMenuScreenPreview(
    @PreviewParameter(ButtonItemsPreviewParameterProvider::class) buttons: List<MenuButtonItem>
) {
    EnglishSimulatorTheme {
        MenuScreen(
            buttonItems = buttons,
            isScreenWithButtons = true,
            title = "Title of the game",
            isVerticalScreen = true,
            onBackClick = {},
        )
    }
}

@HorizontalScreenPreview
@Composable
private fun HorizontalIssueMenuScreenPreview(
    @PreviewParameter(ButtonItemsPreviewParameterProvider::class) buttons: List<MenuButtonItem>
) {
    EnglishSimulatorTheme {
        MenuScreen(
            buttonItems = buttons,
            isScreenWithButtons = true,
            title = "Title of the game",
            isVerticalScreen = false,
            onBackClick = {},
        )
    }
}

@VerticalScreenPreview
@Composable
private fun VerticalMenuScreenWithAddButtonPreview() {
    EnglishSimulatorTheme {
        MenuScreen(
            buttonItems = List(2) {
                MenuButtonItem(title = "Button $it")
            },
            isScreenWithButtons = true,
            title = "Title of the game",
            isVerticalScreen = true,
            onBackClick = {},
            isAddButtonShow = true
        )
    }
}

@VerticalScreenPreview
@Composable
private fun VerticalMenuScreenWithGoalButtonPreview() {
    EnglishSimulatorTheme {
        MenuScreen(
            buttonItems = List(2) {
                MenuButtonItem(title = "Button $it")
            },
            isScreenWithButtons = true,
            title = "Title of the game",
            isVerticalScreen = true,
            onBackClick = {},
            isDailyGoalButtonShow = true
        )
    }
}
