package com.viktoriagavrosh.englishsimulator.ui.features.issue

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
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

    Log.e("123", "IssueMenuScreen start")      // TODO log
    IssueMenuScreen(
        screenStateProvider = { uiState },
        isVerticalScreen = isVerticalScreen,
        onButtonClick = onButtonClick,
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

/**
 * Composable to display menu of quest "Tell about yourself"
 *
 * @param screenStateProvider provides state of screen (ui)
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onButtonClick callback that is executed when button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun IssueMenuScreen(
    screenStateProvider: () -> RequestResult<List<String>>,
    isVerticalScreen: Boolean,
    onButtonClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.e("123", "IssueMenuScreen")      // TODO log
    when (val screenState = screenStateProvider()) {
        is RequestResult.Error -> {
            ErrorScreen(
                onErrorButtonClick = onBackClick,
                modifier = modifier,
            )
        }

        is RequestResult.Loading -> {}
        is RequestResult.Success -> {
            val themes = screenState.data
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
                title = if (isVerticalScreen) {
                    stringResource(R.string.issue_vertical_title)
                } else {
                    stringResource(R.string.issue_horizontal_title)
                },
                isScreenWithButtons = false,
                isVerticalScreen = isVerticalScreen,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalIssueMenuScreenPreview() {
    EnglishSimulatorTheme {
        IssueMenuScreen(
            screenStateProvider = {
                RequestResult.Success(
                    List(8) { "Theme $it" }
                )
            },
            isVerticalScreen = true,
            onButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
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
private fun HorizontalIssueMenuScreenPreview() {
    EnglishSimulatorTheme {
        IssueMenuScreen(
            screenStateProvider = {
                RequestResult.Success(
                    List(8) { "Theme $it" }
                )
            },
            isVerticalScreen = false,
            onButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorVerticalIssueMenuScreenPreview() {
    EnglishSimulatorTheme {
        IssueMenuScreen(
            screenStateProvider = { RequestResult.Error() },
            isVerticalScreen = true,
            onButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
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
private fun ErrorHorizontalIssueMenuScreenPreview() {
    EnglishSimulatorTheme {
        IssueMenuScreen(
            screenStateProvider = { RequestResult.Error() },
            isVerticalScreen = false,
            onButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
