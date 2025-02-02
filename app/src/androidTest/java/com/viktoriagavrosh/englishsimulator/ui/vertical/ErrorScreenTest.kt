package com.viktoriagavrosh.englishsimulator.ui.vertical

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.screens.game.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Rule
import org.junit.Test

class ErrorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun errorScreen_titleIsDisplayed() {
        setErrorScreen()
        composeTestRule.onNodeWithTextById(R.string.something_happened)
            .assertExists("No title on ErrorScreen")
            .assertIsDisplayed()
    }

    @Test
    fun errorScreen_titleHasNoClickAction() {
        setErrorScreen()
        composeTestRule.onNodeWithTextById(R.string.something_happened)
            .assertHasNoClickAction()
    }

    @Test
    fun errorScreen_buttonIsDisplayed() {
        setErrorScreen()
        composeTestRule.onNodeWithTextById(R.string.back)
            .assertExists("No button on ErrorScreen")
            .assertIsDisplayed()
    }

    @Test
    fun errorScreen_buttonHasClickAction() {
        setErrorScreen()
        composeTestRule.onNodeWithTextById(R.string.back)
            .assertHasClickAction()
    }

    @Test
    fun errorScreen_buttonSizeIsRelevant() {
        setErrorScreen()
        composeTestRule.onNodeWithTextById(R.string.back)
            .assertHeightIsAtLeast(48.dp)
    }

    private fun setErrorScreen() {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                ErrorScreen(
                    onErrorButtonClick = {},
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
