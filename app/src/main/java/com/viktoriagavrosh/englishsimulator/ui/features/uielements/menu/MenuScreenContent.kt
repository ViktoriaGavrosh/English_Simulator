package com.viktoriagavrosh.englishsimulator.ui.features.uielements.menu

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
 * TODO fix it
 * @param isVerticalScreen boolean parameter describes screen orientation
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
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                bottom = dimensionResource(R.dimen.padding_super_extra_large)
            )
        )
        if (isVerticalScreen) {
            ButtonColumn(
                buttonItems = buttonItems,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_double_extra_large)
                ),
            )
        } else {
            ButtonRow(
                buttonItems = buttonItems,
                modifier = Modifier,
            )
        }
    }
}

/**
 * Composable to display buttons (vertical screen orientation)
 *
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun ButtonColumn(
    buttonItems: List<MenuButtonItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        for(item in buttonItems) {
            QuestButton(
                onClick = item.onClick,
                text = item.title,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_large))
            )
        }
    }
}

/**
 * Composable to display buttons (horizontal screen orientation)
 *
 *  TODO   fix
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun ButtonRow(
    buttonItems: List<MenuButtonItem>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        for(item in buttonItems) {
            QuestButton(
                onClick = item.onClick,
                text = item.title,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_large))
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
 */
@Composable
private fun QuestButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
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
                MenuButtonItem( title = "Button $it" )
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
            buttonItems = List(2) {
                MenuButtonItem( title = "Button $it" )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
