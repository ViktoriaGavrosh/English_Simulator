package com.viktoriagavrosh.englishsimulator.ui.features.uielements.game

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.elements.BackRow
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.elements.NextButton
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.elements.ScoreBox
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.elements.TextBox
import com.viktoriagavrosh.englishsimulator.ui.features.uielements.game.model.GameQuestion
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display quest "Translate sentences"
 *
 * @param gameQuestion item for ui
 * @param score quest score
 * @param isVerticalScreen boolean parameter describes screen orientation
 * @param onBackClick callback that is executed when back button is clicked
 * @param onNextClick callback that is executed when next button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun GameScreen(
    gameQuestion: GameQuestion,
    score: Int,
    isVerticalScreen: Boolean,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier,
) {

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Center,
    ) {
        BackRow(onBackClick = onBackClick)
        if (isVerticalScreen) {
            ColumnTranslate(
                gameQuestion = gameQuestion,
                score = score,
                onNextClick = onNextClick,
                modifier = Modifier.fillMaxHeight(),
            )
        } else {
            RowTranslate(
                gameQuestion = gameQuestion,
                score = score,
                onNextClick = onNextClick,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

/**
 * Composable to display TranslateScreen content (vertical screen orientation)
 *
 * @param gameQuestion instance [GameQuestion]
 * @param score quest score
 * @param onNextClick callback that is executed when next button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun ColumnTranslate(
    gameQuestion: GameQuestion,
    score: Int,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isAnswerOpen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(
            horizontal = dimensionResource(R.dimen.padding_double_extra_large)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        ScoreBox(score = score)
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
 * @param gameQuestion instance [GameQuestion]
 * @param score quest score
 * @param onNextClick callback that is executed when next button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun RowTranslate(
    gameQuestion: GameQuestion,
    score: Int,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isAnswerOpen by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.padding(
            horizontal = dimensionResource(R.dimen.padding_super_extra_large)
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .padding(bottom = dimensionResource(R.dimen.padding_large)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextBox(
                text = gameQuestion.question,
                modifier = Modifier
            )
            ScoreBox(
                score = score,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_large)),
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_super_extra_large)))
        Column(
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .padding(bottom = dimensionResource(R.dimen.padding_large)),
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
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_large)),
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
            gameQuestion = GameQuestion(
                question = "Ru Text",
                translate = "En Text"
            ),
            score = 0,
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
            gameQuestion = GameQuestion(
                question = "Ru Text",
                translate = "En Text"
            ),
            score = 0,
            isVerticalScreen = false,
            onBackClick = {},
            onNextClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
