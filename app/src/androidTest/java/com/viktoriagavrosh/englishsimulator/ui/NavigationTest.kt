package com.viktoriagavrosh.englishsimulator.ui

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.navigation.EnglishSimulatorApp
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
            EnglishSimulatorApp(
                windowSize = WindowWidthSizeClass.Compact,
                navController = navController
            )
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithTagById(R.string.menu_screen)
            .assertIsDisplayed()
    }

    @Test
    fun navHost_topButtonClick_navigateToRepeatScreen() {
        composeTestRule.onNodeWithTextById(R.string.en_to_ru)
            .performClick()
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.Repeat>()
                ?: false
        )
    }

    @Test
    fun navHost_bottomButtonClick_navigateToRepeatScreen() {
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .performClick()
        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.Repeat>()
                ?: false
        )
    }

    @Test
    fun navHost_backButtonClick_navigateToMenuScreen() {
        composeTestRule.onNodeWithTextById(R.string.ru_to_en)
            .performClick()

        composeTestRule.onNodeWithTagById(R.string.back_button)
            .performClick()

        assertTrue(
            navController.currentBackStackEntry
                ?.destination
                ?.hasRoute<NavigationDestination.Menu>()
                ?: false
        )
    }
}
