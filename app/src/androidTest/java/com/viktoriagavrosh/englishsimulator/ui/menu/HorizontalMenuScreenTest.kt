package com.viktoriagavrosh.englishsimulator.ui.menu

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.fake.FakeSource
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.model.MenuButtonItem
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
import org.junit.Rule
import org.junit.Test

class HorizontalMenuScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun menuScreen_horizontal_backButtonIsDisplayed() {
        setMenuScreen(isBackButtonShow = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertExists("No back button")
            .assertIsDisplayed()
    }

    @Test
    fun menuScreen_horizontal_backButtonIsNotDisplayed() {
        setMenuScreen(isBackButtonShow = false)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertIsNotDisplayed()
    }

    @Test
    fun menuScreen_horizontal_backButtonHasClickAction() {
        setMenuScreen(isBackButtonShow = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertHasClickAction()
    }

    @Test
    fun menuScreen_horizontal_backButtonSizeIsRelevant() {
        setMenuScreen(isBackButtonShow = true)
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun menuScreen_horizontal_titleIsDisplayed() {
        val title = "Menu title"
        setMenuScreen(title = title)
        composeTestRule.onNodeWithText(title)
            .assertExists("No title on MenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun menuScreen_horizontal_titleHasNoClickAction() {
        val title = "Menu title"
        setMenuScreen(title = title)
        composeTestRule.onNodeWithText(title)
            .assertHasNoClickAction()
    }

    @Test
    fun menuScreen_horizontal_menuButtonIsDisplayed() {
        val buttonTitle = "MenuButton"
        setMenuScreen(buttonItems = listOf(MenuButtonItem(title = buttonTitle)))
        composeTestRule.onNodeWithText(buttonTitle)
            .assertExists("No menu button")
            .assertIsDisplayed()

    }

    @Test
    fun menuScreen_horizontal_menuButtonHasClickAction() {
        val buttonTitle = "MenuButton"
        setMenuScreen(buttonItems = listOf(MenuButtonItem(title = buttonTitle)))
        composeTestRule.onNodeWithText(buttonTitle)
            .assertHasClickAction()
    }

    @Test
    fun menuScreen_horizontal_largeMenuButtonSizeIsRelevant() {
        val buttonTitle = "MenuButton"
        setMenuScreen(buttonItems = listOf(MenuButtonItem(title = buttonTitle)))
        composeTestRule.onNodeWithText(buttonTitle)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun menuScreen_horizontal_smallMenuButtonSizeIsRelevant() {
        val buttonTitle = FakeSource.fakeButtonItems[0].title
        setMenuScreen(buttonItems = FakeSource.fakeButtonItems)
        composeTestRule.onNodeWithText(buttonTitle)
            .assertHeightIsAtLeast(48.dp)
    }

    @Test
    fun menuScreen_horizontal_menuCardIsDisplayed() {
        val cardTitle = "MenuCard"
        setMenuScreen(
            buttonItems = listOf(MenuButtonItem(title = cardTitle)),
            isScreenWithButtons = false,
        )
        composeTestRule.onNodeWithText(cardTitle)
            .assertExists("No menu card")
            .assertIsDisplayed()
    }

    @Test
    fun menuScreen_horizontal_menuCardHasClickAction() {
        val cardTitle = "MenuCard"
        setMenuScreen(
            buttonItems = listOf(MenuButtonItem(title = cardTitle)),
            isScreenWithButtons = false,
        )
        composeTestRule.onNodeWithText(cardTitle)
            .assertHasClickAction()
    }

    @Test
    fun menuScreen_horizontal_menuCardSizeIsRelevant() {
        val cardTitle = "MenuCard"
        setMenuScreen(
            buttonItems = listOf(MenuButtonItem(title = cardTitle)),
            isScreenWithButtons = false,
        )
        composeTestRule.onNodeWithText(cardTitle)
            .assertHeightIsAtLeast(48.dp)
    }


    private fun setMenuScreen(
        buttonItems: List<MenuButtonItem> = listOf(MenuButtonItem(title = "button")),
        title: String = "Title",
        isBackButtonShow: Boolean = true,
        isScreenWithButtons: Boolean = true,
    ) {
        composeTestRule.setContent {
            EnglishSimulatorTheme {
                MenuScreen(
                    buttonItems = buttonItems,
                    title = title,
                    isScreenWithButtons = isScreenWithButtons,
                    isVerticalScreen = false,
                    onBackClick = {},
                    modifier = Modifier.fillMaxSize(),
                    isBackButtonShow = isBackButtonShow,
                )
            }
        }
    }
}
