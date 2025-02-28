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
import com.viktoriagavrosh.englishsimulator.ui.features.goal.GoalScreen
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueGameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.issue.IssueMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.StatisticScreen
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateGameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.translate.TranslateMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordGameScreen
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordMenuScreen
import com.viktoriagavrosh.englishsimulator.ui.features.word.WordUpdateScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Composable with navigation between app screens
 *
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param modifier the modifier to be applied to the layout
 */
@Composable
internal fun AppNavigation(
    isVerticalScreen: Boolean,
    modifier: Modifier = Modifier,
) {
    val date = SimpleDateFormat("dd-MM-yyyy", Locale.UK).format(Date())
    val viewModel: UpdateStatisticViewModel = koinViewModel {
        parametersOf(date)
    }

    AppNavigation(
        date = date,
        isVerticalScreen = isVerticalScreen,
        onTranslateScoreUpdate = viewModel::updateTranslateScore,
        onIssueScoreUpdate = viewModel::updateIssueScore,
        onDialogScoreUpdate = viewModel::updateDialogScore,
        onWordScoreUpdate = viewModel::updateWordScore,
        modifier = modifier,
    )
}

/**
 * Composable with navigation between app screens
 *
 * @param date date of current day
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onTranslateScoreUpdate callback that is executed when score is updated
 * @param onIssueScoreUpdate callback that is executed when score is updated
 * @param onDialogScoreUpdate callback that is executed when score is updated
 * @param onWordScoreUpdate callback that is executed when score is updated
 * @param modifier the modifier to be applied to the layout
 * @param navController the navController for this host
 */
@Composable
internal fun AppNavigation(
    date: String,
    isVerticalScreen: Boolean,
    onTranslateScoreUpdate: () -> Unit,
    onIssueScoreUpdate: () -> Unit,
    onDialogScoreUpdate: () -> Unit,
    onWordScoreUpdate: () -> Unit,
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
                onStatisticButtonClick = {
                    navController.navigate(NavigationDestination.Statistic)
                },
                onDailyGoalButtonClick = {
                    navController.navigate(NavigationDestination.DailyGoal)
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
                onTranslateScoreUpdate = onTranslateScoreUpdate,
                modifier = modifier.testTag(stringResource(R.string.translate_game_screen)),
            )
        }
        composable<NavigationDestination.IssueGame> { backStackEntry ->
            val theme = backStackEntry.toRoute<NavigationDestination.IssueGame>().theme
            IssueGameScreen(
                isVerticalScreen = isVerticalScreen,
                theme = theme,
                onBackClick = { navController.navigateUp() },
                onIssueScoreUpdate = onIssueScoreUpdate,
                modifier = modifier.testTag(stringResource(R.string.issue_game_screen)),
            )
        }
        composable<NavigationDestination.DialogGame> {
            DialogGameScreen(
                isVerticalScreen = isVerticalScreen,
                onBackClick = { navController.navigateUp() },
                onDialogScoreUpdate = onDialogScoreUpdate,
                modifier = modifier.testTag(stringResource(R.string.dialog_game_screen)),
            )
        }
        composable<NavigationDestination.WordMenu> {
            WordMenuScreen(
                isVerticalScreen = isVerticalScreen,
                onButtonClick = { theme, quest ->
                    navController.navigate(
                        NavigationDestination.WordGame(
                            theme = theme,
                            quest = quest
                        )
                    )
                },
                onBackClick = { navController.navigateUp() },
                onAddButtonClick = {
                    navController.navigate(NavigationDestination.WordUpdate(wordId = 0))
                },
                modifier = modifier.testTag(stringResource(R.string.word_menu_screen)),
            )
        }
        composable<NavigationDestination.WordGame> { backStackEntry ->
            val data = backStackEntry.toRoute<NavigationDestination.WordGame>()
            WordGameScreen(
                isVerticalScreen = isVerticalScreen,
                quest = data.quest,
                theme = data.theme,
                onEditButtonClick = { id ->
                    navController.navigate(
                        NavigationDestination.WordUpdate(
                            wordId = id,
                        )
                    )
                },
                onBackClick = { navController.navigateUp() },
                onWordScoreUpdate = onWordScoreUpdate,
                modifier = modifier.testTag(stringResource(R.string.word_game_screen)),
            )
        }
        composable<NavigationDestination.WordUpdate> { backStackEntry ->
            val wordId = backStackEntry.toRoute<NavigationDestination.WordUpdate>().wordId
            WordUpdateScreen(
                onBackClick = { navController.navigateUp() },
                modifier = Modifier.testTag(stringResource(R.string.issue_game_screen)),
                wordId = wordId,
            )
        }
        composable<NavigationDestination.Statistic> {
            StatisticScreen(
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.statistic_screen)),
            )
        }
        composable<NavigationDestination.DailyGoal> {
            GoalScreen(
                date = date,
                onBackClick = { navController.navigateUp() },
                onEditGoalsClick = { navController.navigate(NavigationDestination.UpdateGoal) },
                modifier = Modifier.testTag(stringResource(R.string.goal_screen)),
            )
        }
        composable<NavigationDestination.UpdateGoal> {
            /*
            UpdateGoalScreen(
                onBackClick = { navController.navigateUp() },
                modifier = modifier.testTag(stringResource(R.string.update_goal_screen)),
            )
             */
        }
    }
}
