package com.viktoriagavrosh.englishsimulator.ui.vertical

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.navigation.AppNavigation
import com.viktoriagavrosh.englishsimulator.ui.navigation.NavigationDestination
import com.viktoriagavrosh.englishsimulator.utils.onNodeWithContentDescriptionById
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
                onTranslateScoreUpdate = {},
                onIssueScoreUpdate = {},
                onDialogScoreUpdate = {},
                onWordScoreUpdate = {},
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
    fun navHost_startMenuScreen_wordButtonClick_navigateToWordMenuScreen() {
        navigateToWordMenuScreen()
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordMenu>()
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
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
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
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
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
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
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
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
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

    @Test
    fun navHost_wordMenuScreen_backButtonClick_navigateToStartMenuScreen() {
        navigateToWordMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.StartMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_wordMenuScreen_buttonClick_navigateToWordGameScreen() {
        navigateToWordGameScreen()
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordGame>()
                ?: false
        )
    }

    @Test
    fun navHost_wordMenuScreen_addButtonClick_navigateToWordUpdateScreen() {
        navigateToWordMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .performClick()
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordUpdate>()
                ?: false
        )
    }

    @Test
    fun navHost_wordGameScreen_backButtonClick_navigateToWordMenuScreen() {
        navigateToWordGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.back)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_wordGameScreen_editButtonClick_navigateToWordUpdateScreen() {
        navigateToWordGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordUpdate>()
                ?: false
        )
    }

    @Test
    fun navHost_wordUpdateScreen_cancelButtonClick_navigateToWordGameScreen() {
        navigateToWordUpdateScreen()
        composeTestRule.onNodeWithTextById(R.string.cancel)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordGame>()
                ?: false
        )
    }

    @Test
    fun navHost_wordUpdateScreen_saveButtonClick_navigateToWordGameScreen() {
        navigateToWordUpdateScreen()
        composeTestRule.onNodeWithTextById(R.string.save)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordGame>()
                ?: false
        )
    }

    @Test
    fun navHost_wordUpdateScreen_deleteButtonClick_navigateToWordGameScreen() {
        navigateToWordUpdateScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.delete_word)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordGame>()
                ?: false
        )
    }

    @Test
    fun navHost_wordUpdateScreen_cancelButtonClick_navigateToWordMenuScreen() {
        navigateToWordMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .performClick()
        composeTestRule.onNodeWithTextById(R.string.cancel)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_wordUpdateScreen_saveButtonClick_navigateToWordMenuScreen() {
        navigateToWordMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .performClick()
        composeTestRule.onNodeWithTextById(R.string.english_text)
            .performTextInput("En text")
        composeTestRule.onNodeWithTextById(R.string.russian_text)
            .performTextInput("Ru text")
        composeTestRule.onNodeWithTextById(R.string.theme)
            .performTextInput("Theme")
        composeTestRule.onNodeWithTextById(R.string.save)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_wordUpdateScreen_deleteButtonClick_navigateToWordMenuScreen() {
        navigateToWordMenuScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.add)
            .performClick()
        composeTestRule.onNodeWithContentDescriptionById(R.string.delete_word)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.WordMenu>()
                ?: false
        )
    }

    @Test
    fun navHost_startMenuScreen_statisticButtonClick_navigateToStatisticScreen() {
        navigateToStatisticScreen()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.Statistic>()
                ?: false
        )
    }

    private fun navigateToTranslateMenuScreen() {
        composeTestRule.onNodeWithTextById(R.string.translate_button_title)
            .performClick()
    }

    private fun navigateToIssueMenuScreen() {
        composeTestRule.onNodeWithTextById(R.string.issue_button_title)
            .performClick()
    }

    private fun navigateToTranslateGameScreen(isToEnglish: Boolean) {
        navigateToTranslateMenuScreen()
        composeTestRule.onNodeWithTextById(
            if (isToEnglish) R.string.ru_to_en else R.string.en_to_ru
        )
            .performClick()
    }

    private fun navigateToIssueGameScreen() {
        navigateToIssueMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.vertical_menu_content_tag)
            .performTouchInput { this.swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.all_themes)
            .performClick()
    }

    private fun navigateToDialogGameScreen() {
        composeTestRule.onNodeWithTextById(R.string.dialog_button_title)
            .performClick()
    }

    private fun navigateToWordMenuScreen() {
        composeTestRule.onNodeWithTextById(R.string.word_button_title)
            .performClick()
    }

    private fun navigateToWordGameScreen() {
        navigateToWordMenuScreen()
        composeTestRule.onNodeWithTagById(R.string.vertical_menu_content_tag)
            .performTouchInput { this.swipeUp() }
        composeTestRule.onNodeWithTextById(R.string.all_themes)
            .performClick()
    }

    private fun navigateToWordUpdateScreen() {
        navigateToWordGameScreen()
        composeTestRule.onNodeWithContentDescriptionById(R.string.edit)
            .performClick()
    }

    private fun navigateToStatisticScreen() {
        composeTestRule.onNodeWithTextById(R.string.statistic_button_title)
            .performClick()
    }
}
