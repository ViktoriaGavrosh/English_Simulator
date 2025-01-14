package com.viktoriagavrosh.englishsimulator.ui.features.screens.game

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.elements.IconRow
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.elements.NextButton
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.elements.ScoreBox
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.elements.TextBox
import com.viktoriagavrosh.englishsimulator.ui.features.screens.game.model.GameQuestion
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display quest
 *
 * @param gameQuestionProvider provides item for ui
 * @param scoreProvider provides score of game
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param onNextClick callback that is executed when next button is clicked
 * @param modifier the modifier to be applied to this layout node
 * @param isUpdateButtonShow if true Edit button will be shown
 * @param onUpdateButtonClick callback that is executed when edit button is clicked
 */
@Composable
internal fun GameScreen(
    gameQuestionProvider: () -> GameQuestion,
    scoreProvider: () -> Int,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier,
    isUpdateButtonShow: Boolean = false,
    onUpdateButtonClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        IconRow(
            iconId = R.drawable.ic_back,
            contentDescription = stringResource(R.string.back),
            onIconClick = onBackClick,
            modifier = Modifier.testTag(stringResource(R.string.back_button)),
        )
        if (isUpdateButtonShow) {
            IconRow(
                iconId = R.drawable.ic_edit,
                contentDescription = stringResource(R.string.edit),
                onIconClick = onUpdateButtonClick,
                isLeft = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(stringResource(R.string.edit_button))
            )
        }
        if (isVerticalScreen) {
            ColumnTranslate(
                gameQuestionProvider = gameQuestionProvider,
                scoreProvider = scoreProvider,
                onNextClick = onNextClick,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = dimensionResource(R.dimen.padding_large)),
            )
        } else {
            RowTranslate(
                gameQuestionProvider = gameQuestionProvider,
                scoreProvider = scoreProvider,
                onNextClick = onNextClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(R.dimen.padding_large)),
            )
        }
    }
}

/**
 * Composable to display TranslateScreen content (vertical screen orientation)
 *
 * @param gameQuestionProvider provides item for ui
 * @param scoreProvider provides score of game
 * @param onNextClick callback that is executed when next button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun ColumnTranslate(
    gameQuestionProvider: () -> GameQuestion,
    scoreProvider: () -> Int,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isAnswerOpen by remember { mutableStateOf(false) }
    val gameQuestion = gameQuestionProvider()

    Column(
        modifier = modifier.padding(
            horizontal = dimensionResource(R.dimen.padding_extra_large)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        ScoreBox(score = scoreProvider())
        TextBox(
            text = gameQuestion.question,
            modifier = Modifier
        )
        TextBox(
            text = gameQuestion.translate,
            isTextShow = isAnswerOpen,
            modifier = Modifier.clickable {
                isAnswerOpen = !isAnswerOpen
            }
        )
        NextButton(
            onClick = {
                isAnswerOpen = false
                onNextClick()
            },
        )
    }
}

/**
 * Composable to display TranslateScreen content (horizontal screen orientation)
 *
 * @param gameQuestionProvider provides item for ui
 * @param scoreProvider provides score of game
 * @param onNextClick callback that is executed when next button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun RowTranslate(
    gameQuestionProvider: () -> GameQuestion,
    scoreProvider: () -> Int,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isAnswerOpen by remember { mutableStateOf(false) }
    val gameQuestion = gameQuestionProvider()

    Row(
        modifier = modifier.padding(
            horizontal = dimensionResource(R.dimen.padding_double_large)
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .padding(bottom = dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextBox(
                text = gameQuestion.question,
                modifier = Modifier
            )
            ScoreBox(
                score = scoreProvider(),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_double_large)))
        Column(
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .padding(bottom = dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextBox(
                text = gameQuestion.translate,
                isTextShow = isAnswerOpen,
                modifier = Modifier.clickable {
                    isAnswerOpen = !isAnswerOpen
                }
            )
            NextButton(
                onClick = {
                    isAnswerOpen = false
                    onNextClick()
                },
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
            )
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerticalTranslateScreenPreview() {
    EnglishSimulatorTheme {
        GameScreen(
            gameQuestionProvider = {
                GameQuestion(
                    question = "Ru Text",
                    translate = "En Text"
                )
            },
            scoreProvider = { 0 },
            isVerticalScreen = true,
            onBackClick = {},
            onNextClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 1000)
@Preview(
    showBackground = true,
    name = "Dark",
    widthDp = 1000,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HorizontalTranslateScreenPreview() {
    EnglishSimulatorTheme {
        GameScreen(
            gameQuestionProvider = {
                GameQuestion(
                    question = "Ru Text",
                    translate = "En Text"
                )
            },
            scoreProvider = { 0 },
            isVerticalScreen = false,
            onBackClick = {},
            onNextClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EditVerticalTranslateScreenPreview() {
    EnglishSimulatorTheme {
        GameScreen(
            gameQuestionProvider = {
                GameQuestion(
                    question = "Ru Text",
                    translate = "En Text"
                )
            },
            scoreProvider = { 0 },
            isVerticalScreen = true,
            onBackClick = {},
            onNextClick = {},
            modifier = Modifier.fillMaxSize(),
            isUpdateButtonShow = true,
        )
    }
}
