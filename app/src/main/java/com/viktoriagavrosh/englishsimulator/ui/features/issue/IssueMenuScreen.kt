package com.viktoriagavrosh.englishsimulator.ui.features.issue

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import org.koin.androidx.compose.koinViewModel

/**
 * Composable to display menu of quest "Tell about yourself"
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onButtonClick callback that is executed when button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun IssueMenuScreen(
    isVerticalScreen: Boolean,
    onButtonClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: IssueMenuViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is RequestResult.Error -> {
            ErrorScreen(
                onErrorButtonClick = onBackClick,
                modifier = modifier,
            )
        }

        is RequestResult.Loading -> {}
        is RequestResult.Success -> {
            val themes = uiState.data ?: emptyList()
            val buttonItems: MutableList<MenuButtonItem> = mutableListOf()

            themes.forEach {
                buttonItems.add(
                    MenuButtonItem(
                        title = it,
                        onClick = { onButtonClick(it) },
                    )
                )
            }
            buttonItems.add(
                MenuButtonItem(
                    title = stringResource(R.string.all_themes),
                    onClick = { onButtonClick("") }
                )
            )

            MenuScreen(
                buttonItems = buttonItems,
                title = stringResource(R.string.issue_title),
                isVerticalScreen = isVerticalScreen,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }
    }
}
