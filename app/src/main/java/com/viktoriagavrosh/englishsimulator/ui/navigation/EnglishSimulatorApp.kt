package com.viktoriagavrosh.englishsimulator.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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

@Composable
fun EnglishSimulatorApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController()
) {
    val isVerticalScreen = windowSize != WindowWidthSizeClass.Expanded

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Menu,
    ) {
        composable<NavigationDestination.Menu> {
            MenuScreen(
                isVerticalScreen = isVerticalScreen,
                onRuToEnClick = {
                    navController.navigate(NavigationDestination.Repeat(Quest.RuToEn))
                },
                onEnToRuClick = {
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
