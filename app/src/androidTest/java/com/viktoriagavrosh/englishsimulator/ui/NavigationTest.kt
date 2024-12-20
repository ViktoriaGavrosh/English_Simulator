package com.viktoriagavrosh.englishsimulator.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.navigation.AppNavigation
import com.viktoriagavrosh.englishsimulator.ui.navigation.NavigationDestination
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTagById
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithTextById
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavigation(
                isVerticalScreen = true,
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithTagById(R.string.start_menu_screen)
            .assertIsDisplayed()
    }

    @Test
    fun navHost_startMenuScreen_translateButtonClick_navigateToTranslateMenuScreen() {
        navigateToTranslateMenuScreen()
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.TranslateMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_startMenuScreen_issueButtonClick_navigateToIssueMenuScreen() {
        navigateToIssueMenuScreen()
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.IssueMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_translateMenuScreen_toEnglishButtonClick_navigateToTranslateGameScreen() {
        navigateToTranslateGameScreen(isToEnglish = true)
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.TranslateGame>()
                ?: false
        )
    }

    @Test
    fun navHost_translateMenuScreen_toRussianButtonClick_navigateToTranslateGameScreen() {
        navigateToTranslateGameScreen(isToEnglish = false)
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.TranslateGame>()
                ?: false
        )
    }

    @Test
    fun navHost_translateMenuScreen_backButtonClick_navigateToStartMenuScreen() {
        navigateToTranslateMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.back_button)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.StartMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_issueMenuScreen_buttonClick_navigateToIssueGameScreen() {
        navigateToIssueGameScreen()
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.IssueGame>()
                ?: false
        )
    }

    @Test
    fun navHost_issueMenuScreen_backButtonClick_navigateToStartMenuScreen() {
        navigateToIssueMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.back_button)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.StartMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_translateGameScreen_backButtonClick_navigateToTranslateMenuScreen() {
        navigateToTranslateGameScreen(isToEnglish = true)
        composeTestRule.onNodeWithTagById(R.string.back_button)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.TranslateMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_issueGameScreen_backButtonClick_navigateToIssueMenuScreen() {
        navigateToIssueGameScreen()
        composeTestRule.onNodeWithTagById(R.string.back_button)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.IssueMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_startMenuScreen_dialogButtonClick_navigateToDialogGameScreen() {
        navigateToDialogGameScreen()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.DialogGame>()
                ?: false
        )
    }

    private fun navigateToTranslateMenuScreen() {
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.TranslateMenu>()
                ?: false
        )
    }

    private fun navigateToIssueMenuScreen() {
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .performClick()
    }

    private fun navigateToTranslateGameScreen(isToEnglish: Boolean) {
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .performClick()
        composeTestRule.onNodeWithTextById(
            if (isToEnglish) R.string.ru_to_en else R.string.en_to_ru
        )
            .performClick()
    }

    private fun navigateToIssueGameScreen() {
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .performClick()
        composeTestRule.onNodeWithTextById(R.string.all_themes)
            .performClick()
    }

    private fun navigateToDialogGameScreen() {
        composeTestRule.onNodeWithTextById(R.string.dialog_button_title)
            .performClick()
    }
}
