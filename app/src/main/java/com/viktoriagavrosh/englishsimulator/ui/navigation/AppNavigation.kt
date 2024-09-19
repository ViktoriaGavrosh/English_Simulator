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
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.MenuScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.repeat.RepeatScreen

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
        startDestination = NavigationDestination.Menu,
    ) {
        composable<NavigationDestination.Menu> {
            MenuScreen(
                isVerticalScreen = isVerticalScreen,
                onFirstButtonClick = {
                    navController.navigate(NavigationDestination.Repeat(Quest.RuToEn))
                },
                onSecondButtonClick = {
                    navController.navigate(NavigationDestination.Repeat(Quest.EnToRu))
                },
                modifier = modifier.testTag(stringResource(R.string.menu_screen))
            )
        }
        composable<NavigationDestination.Repeat> { backStackEntry ->
            val quest = backStackEntry.toRoute<NavigationDestination.Repeat>().quest
            RepeatScreen(
                isVerticalScreen = isVerticalScreen,
                quest = quest,
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.repeat_screen))
            )
        }
    }
}
