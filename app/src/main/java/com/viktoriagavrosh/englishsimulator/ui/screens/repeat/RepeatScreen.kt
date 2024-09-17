package com.viktoriagavrosh.englishsimulator.ui.screens.repeat

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import com.viktoriagavrosh.englishsimulator.ui.screens.repeat.elements.BackRow
import com.viktoriagavrosh.englishsimulator.ui.screens.repeat.elements.ErrorScreen
import com.viktoriagavrosh.englishsimulator.ui.screens.repeat.elements.NextButton
import com.viktoriagavrosh.englishsimulator.ui.screens.repeat.elements.ScoreBox
import com.viktoriagavrosh.englishsimulator.ui.screens.repeat.elements.TextBox
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display quest "Repeat sentences"
 */
@Composable
internal fun RepeatScreen(
    isVerticalScreen: Boolean,
    quest: Quest,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: RepeatViewModel = viewModel(factory = RepeatViewModel.Factory)
    val uiState by viewModel.uiState.collectAsState()
    val count = viewModel.count.collectAsState()

    RepeatScreen(
        modifier = modifier,
        isError = uiState.isError,
        onBackClick = onBackClick,
        isVerticalScreen = isVerticalScreen,
        sentence = uiState.sentence,
        score = count.value,
        isRuToEn = quest == Quest.RuToEn,
        onNextClick = viewModel::updateSentence,
        onTryAgainClick = viewModel::initUiState,
    )
}

@Composable
internal fun RepeatScreen(
    modifier: Modifier,
    isError: Boolean,
    onBackClick: () -> Unit,
    isVerticalScreen: Boolean,
    sentence: Sentence,
    score: Int,
    isRuToEn: Boolean,
    onNextClick: () -> Unit,
    onTryAgainClick: () -> Unit,
) {
    if (isError) {
        ErrorScreen(
            onTryAgainClick = onTryAgainClick,
            modifier = modifier
        )
    } else {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Center,
        ) {
            BackRow(onBackClick = onBackClick)
            if (isVerticalScreen) {
                ColumnRepeat(
                    sentence = sentence,
                    score = score,
                    isRuToEn = isRuToEn,
                    onNextClick = onNextClick,
                    modifier = Modifier.fillMaxHeight(),
                )
            } else {
                RowRepeat(
                    sentence = sentence,
                    score = score,
                    isRuToEn = isRuToEn,
                    onNextClick = onNextClick,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun ColumnRepeat(
    sentence: Sentence,
    score: Int,
    isRuToEn: Boolean,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isAnswerOpen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(
            horizontal = dimensionResource(R.dimen.padding_extra_large)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        ScoreBox(score = score)
        TextBox(
            text = if (isRuToEn) sentence.ruText else sentence.enText,
            modifier = Modifier
        )
        TextBox(
            text = if (isRuToEn) sentence.enText else sentence.ruText,
            isTextShow = isAnswerOpen,
            modifier = Modifier.clickable {
                isAnswerOpen = !isAnswerOpen
            }
        )
        NextButton(
            onNextClick = {
                isAnswerOpen = false
                onNextClick()
            },
        )
    }
}

@Composable
private fun RowRepeat(
    sentence: Sentence,
    score: Int,
    isRuToEn: Boolean,
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
                text = if (isRuToEn) sentence.ruText else sentence.enText,
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
                text = if (isRuToEn) sentence.enText else sentence.ruText,
                isTextShow = isAnswerOpen,
                modifier = Modifier.clickable {
                    isAnswerOpen = !isAnswerOpen
                }
            )
            NextButton(
                onNextClick = {
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
fun VerticalRepeatScreenPreview() {
    EnglishSimulatorTheme {
        RepeatScreen(
            isError = false,
            sentence = Sentence(
                ruText = "Ru Text",
                enText = "En Text"
            ),
            score = 0,
            isVerticalScreen = true,
            isRuToEn = true,
            onBackClick = {},
            onNextClick = {},
            onTryAgainClick = {},
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
fun HorizontalRepeatScreenPreview() {
    EnglishSimulatorTheme {
        RepeatScreen(
            isError = false,
            sentence = Sentence(
                ruText = "Ru Text",
                enText = "En Text"
            ),
            score = 0,
            isVerticalScreen = false,
            isRuToEn = true,
            onBackClick = {},
            onNextClick = {},
            onTryAgainClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
