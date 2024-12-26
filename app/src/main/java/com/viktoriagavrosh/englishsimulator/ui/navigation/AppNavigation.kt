package com.viktoriagavrosh.englishsimulator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.StartMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.dialog.DialogGameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueGameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateGameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateMenuScreen

/**
 * Composable with navigation between app screens
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param modifier the modifier to be applied to the layout
 * @param navController the navController for this host
 */
@Composable
internal fun AppNavigation(
    isVerticalScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.StartMenu,
    ) {
        composable<NavigationDestination.StartMenu> {
            StartMenuScreen(
                isVerticalScreen = isVerticalScreen,
                onTranslateButtonClick = {
                    navController.navigate(NavigationDestination.TranslateMenu)
                },
                onIssueButtonClick = {
                    navController.navigate(NavigationDestination.IssueMenu)
                },
                onDialogButtonClick = {
                    navController.navigate(NavigationDestination.DialogGame)
                },
                onWordButtonClick = {
                    navController.navigate(NavigationDestination.WordMenu)
                },
                modifier = modifier.testTag(stringResource(R.string.start_menu_screen)),
            )
        }
        composable<NavigationDestination.TranslateMenu> {
            TranslateMenuScreen(
                isVerticalScreen = isVerticalScreen,
                onToEnglishButtonClick = {
                    navController.navigate(NavigationDestination.TranslateGame(Quest.RuToEn))
                },
                onToRussianButtonClick = {
                    navController.navigate(NavigationDestination.TranslateGame(Quest.EnToRu))
                },
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.translate_menu_screen)),
            )
        }
        composable<NavigationDestination.IssueMenu> {
            IssueMenuScreen(
                isVerticalScreen = isVerticalScreen,
                onButtonClick = { theme ->
                    navController.navigate(NavigationDestination.IssueGame(theme))
                },
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.issue_menu_screen)),
            )
        }
        composable<NavigationDestination.TranslateGame> { backStackEntry ->
            val quest = backStackEntry.toRoute<NavigationDestination.TranslateGame>().quest
            TranslateGameScreen(
                isVerticalScreen = isVerticalScreen,
                quest = quest,
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.translate_game_screen)),
            )
        }
        composable<NavigationDestination.IssueGame> { backStackEntry ->
            val theme = backStackEntry.toRoute<NavigationDestination.IssueGame>().theme
            IssueGameScreen(
                isVerticalScreen = isVerticalScreen,
                theme = theme,
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.issue_game_screen)),
            )
        }
        composable<NavigationDestination.DialogGame> {
            DialogGameScreen(
                isVerticalScreen = isVerticalScreen,
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.dialog_game_screen)),
            )
        }
        composable<NavigationDestination.WordMenu> {
            /*
            WordMenuScreen(
                isVerticalScreen = isVerticalScreen,
                onButtonClick = { theme ->
                    navController.navigate(NavigationDestination.IssueGame(theme))
                },
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.issue_menu_screen)),
            )

             */
        }
        composable<NavigationDestination.WordGame> { backStackEntry ->
            val data = backStackEntry.toRoute<NavigationDestination.WordGame>()
            /*
            WordGameScreen(
                isVerticalScreen = isVerticalScreen,
                theme = data.theme,
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.issue_game_screen)),
            )

             */
        }
        composable<NavigationDestination.WordUpdate> { backStackEntry ->
            val wordId = backStackEntry.toRoute<NavigationDestination.WordUpdate>().wordId
            /*
            WordGameScreen(
                isVerticalScreen = isVerticalScreen,
                theme = data.theme,
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.issue_game_screen)),
            )

             */
        }
    }
}
