package com.viktoriagavrosh.englishsimulator.ui.features.word

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.ErrorResultPreviewParameterProvider
import com.viktoriagavrosh.englishsimulator.utils.HorizontalScreenPreview
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview
import org.koin.androidx.compose.koinViewModel

/**
 * Composable to display menu of quest "FlashCards"
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onButtonClick callback that is executed when button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun WordMenuScreen(
    isVerticalScreen: Boolean,
    onButtonClick: (String, Quest) -> Unit,
    onBackClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: WordMenuViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()

    WordMenuScreen(
        screenStateProvider = { uiState },
        isVerticalScreen = isVerticalScreen,
        onButtonClick = { theme -> onButtonClick(theme, language) },
        onBackClick = onBackClick,
        dropdownMenuOptions = Quest.entries.map { it.text },
        dropdownMenuSelectedOptionProvider = { language.text },
        onDropdownMenuValueChange = viewModel::updateLanguage,
        onAddButtonClick = onAddButtonClick,
        modifier = modifier,
    )
}

/**
 * Composable to display menu of quest "FlashCards"
 *
 * @param screenStateProvider provides state of screen (ui)
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onButtonClick callback that is executed when button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param dropdownMenuSelectedOptionProvider provides selected item for dropdownMenu
 * @param dropdownMenuOptions list of items to select for dropdownMenu
 * @param onDropdownMenuValueChange callback that is executed when dropdown menu value is changed
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun WordMenuScreen(
    screenStateProvider: () -> RequestResult<List<String>>,
    isVerticalScreen: Boolean,
    onButtonClick: (String) -> Unit,
    onBackClick: () -> Unit,
    dropdownMenuSelectedOptionProvider: () -> String,
    dropdownMenuOptions: List<String>,
    onDropdownMenuValueChange: (String) -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                    stringResource(R.string.word_vertical_title)
                } else {
                    stringResource(R.string.word_horizontal_title)
                },
                isScreenWithButtons = false,
                isVerticalScreen = isVerticalScreen,
                onBackClick = onBackClick,
                modifier = modifier,
                dropdownMenuOptions = dropdownMenuOptions,
                dropdownMenuSelectedOptionProvider = dropdownMenuSelectedOptionProvider,
                onDropdownMenuValueChange = onDropdownMenuValueChange,
                isAddButtonShow = true,
                onAddButtonClick = onAddButtonClick,
            )
        }
    }
}

@VerticalScreenPreview
@Composable
private fun VerticalWordMenuScreenPreview(
    @PreviewParameter(ErrorResultPreviewParameterProvider::class) result: RequestResult<List<String>>
) {
    EnglishSimulatorTheme {
        WordMenuScreen(
            screenStateProvider = { result },
            isVerticalScreen = true,
            onButtonClick = {},
            onBackClick = {},
            dropdownMenuOptions = listOf("first", "second"),
            dropdownMenuSelectedOptionProvider = { "first" },
            onDropdownMenuValueChange = {},
            onAddButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@HorizontalScreenPreview
@Composable
private fun HorizontalWordMenuScreenPreview(
    @PreviewParameter(ErrorResultPreviewParameterProvider::class) result: RequestResult<List<String>>
) {
    EnglishSimulatorTheme {
        WordMenuScreen(
            screenStateProvider = { result },
            isVerticalScreen = false,
            onButtonClick = {},
            onBackClick = {},
            dropdownMenuOptions = listOf("first", "second"),
            dropdownMenuSelectedOptionProvider = { "first" },
            onDropdownMenuValueChange = {},
            onAddButtonClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
