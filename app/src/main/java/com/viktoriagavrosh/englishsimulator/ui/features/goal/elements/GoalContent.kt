package com.viktoriagavrosh.englishsimulator.ui.features.goal.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.model.Goal
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to display daily goals of all quests
 *
 * @param dayStatisticProvider provides scores of current day
 * @param goal daily goals for all quests
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun GoalContent(
    dayStatisticProvider: () -> Statistic,
    goal: Goal,
    modifier: Modifier = Modifier,
) {
    val statistic = dayStatisticProvider()
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_double_medium)),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.padding_medium))
            .testTag(stringResource(R.string.questgoal_column_tag)),
    ) {
        QuestGoal(
            title = stringResource(R.string.translate_button_title),
            goalScore = goal.translateGoal,
            questScore = statistic.translateScore,
            modifier = Modifier.fillMaxWidth()
        )
        QuestGoal(
            title = stringResource(R.string.issue_button_title),
            goalScore = goal.issueGoal,
            questScore = statistic.issueScore,
            modifier = Modifier.fillMaxWidth()
        )
        QuestGoal(
            title = stringResource(R.string.dialog_button_title),
            goalScore = goal.dialogGoal,
            questScore = statistic.dialogScore,
            modifier = Modifier.fillMaxWidth()
        )
        QuestGoal(
            title = stringResource(R.string.word_button_title),
            goalScore = goal.wordGoal,
            questScore = statistic.wordScore,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@VerticalScreenPreview
@Composable
private fun GoalContentPreview() {
    EnglishSimulatorTheme {
        GoalContent(
            dayStatisticProvider = {
                Statistic(
                    translateScore = 5,
                    issueScore = 7,
                    dialogScore = 9,
                    wordScore = 12,
                )
            },
            goal = Goal(
                translateGoal = 15,
                issueGoal = 3,
                dialogGoal = 9,
                wordGoal = 87,
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}
