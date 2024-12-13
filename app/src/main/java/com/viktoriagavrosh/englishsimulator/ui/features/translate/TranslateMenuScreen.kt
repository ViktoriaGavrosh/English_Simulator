package com.viktoriagavrosh.englishsimulator.ui.features.translate

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display menu of quest "Translate sentences"
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onToEnglishButtonClick callback that is executed when toEnglish button is clicked
 * @param onToRussianButtonClick callback that is executed when toRussian button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
fun TranslateMenuScreen(
    isVerticalScreen: Boolean,
    onToEnglishButtonClick: () -> Unit,
    onToRussianButtonClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val buttonItems = listOf(
        MenuButtonItem(
            title = stringResource(R.string.ru_to_en),
            onClick = onToEnglishButtonClick,
        ),
        MenuButtonItem(
            title = stringResource(R.string.en_to_ru),
            onClick = onToRussianButtonClick,
        )
    )

    MenuScreen(
        buttonItems = buttonItems,
        title = if (isVerticalScreen) {
            stringResource(R.string.translate_vertical_title)
        } else {
            stringResource(R.string.translate_horizontal_title)
        },
        isVerticalScreen = isVerticalScreen,
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalTranslateMenuScreenPreview() {
    EnglishSimulatorTheme {
        TranslateMenuScreen(
            isVerticalScreen = true,
            onToEnglishButtonClick = {},
            onToRussianButtonClick = {},
            onBackClick = {},
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
private fun HorizontalTranslateScreenContentPreview() {
    EnglishSimulatorTheme {
        TranslateMenuScreen(
            isVerticalScreen = false,
            onToEnglishButtonClick = {},
            onToRussianButtonClick = {},
            onBackClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
