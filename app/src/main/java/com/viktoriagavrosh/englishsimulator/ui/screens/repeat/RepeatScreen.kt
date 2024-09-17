package com.viktoriagavrosh.englishsimulator.ui.screens.repeat

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.model.Sentence
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

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
        onBackClick = onBackClick,
        isVerticalScreen = isVerticalScreen,
        sentence = uiState.sentence,
        score = count.value,
        isRuToEn = quest == Quest.RuToEn,
        onNextClick = viewModel::updateSentence,
    )
}

@Composable
internal fun RepeatScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    isVerticalScreen: Boolean,
    sentence: Sentence,
    score: Int,
    isRuToEn: Boolean,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Center,
    ) {
        BackRow(
            onBackClick = onBackClick,
        )
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

@Composable
private fun BackRow(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        OutlinedIconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.padding_medium),
                    start = dimensionResource(R.dimen.padding_medium)
                ),
            colors = IconButtonDefaults.outlinedIconButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
            ),
            border = IconButtonDefaults.outlinedIconButtonBorder(false)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = stringResource(R.string.back),
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                tint = MaterialTheme.colorScheme.primary,
            )
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
        CountBox(
            count = score
        )
        TextBox(
            text = if (isRuToEn) sentence.ruText else sentence.enText,
            modifier = Modifier
        )
        TextBox(
            text = if (isRuToEn) sentence.enText else sentence.ruText,
            isOpen = isAnswerOpen,
            isQuestion = false,
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
            CountBox(
                count = score,
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
                isOpen = isAnswerOpen,
                isQuestion = false,
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

@Composable
private fun TextBox(
    text: String,
    modifier: Modifier = Modifier,
    isQuestion: Boolean = true,
    isOpen: Boolean = false,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_shapes)))
            .aspectRatio(1.5F)
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center,
    ) {
        if (isQuestion || isOpen) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.ic_question),
                contentDescription = stringResource(R.string.answer),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
            )
        }
    }
}

@Composable
private fun CountBox(
    count: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(
                width = dimensionResource(R.dimen.countBox_border),
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(dimensionResource(R.dimen.corner_shapes))
            )
            .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_shapes)))
            .size(dimensionResource(R.dimen.countBox_size))
            .background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
private fun NextButton(
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onNextClick,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_next),
            contentDescription = stringResource(R.string.next_question),
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_extra_large))
                .size(dimensionResource(R.dimen.icon_size))
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VerticalRepeatScreenPreview() {
    EnglishSimulatorTheme {
        RepeatScreen(
            sentence = Sentence(
                ruText = "Ru Text",
                enText = "En Text"
            ),
            score = 0,
            isVerticalScreen = true,
            isRuToEn = true,
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
fun HorizontalRepeatScreenPreview() {
    EnglishSimulatorTheme {
        RepeatScreen(
            sentence = Sentence(
                ruText = "Ru Text",
                enText = "En Text"
            ),
            score = 0,
            isVerticalScreen = false,
            isRuToEn = true,
            onBackClick = {},
            onNextClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
