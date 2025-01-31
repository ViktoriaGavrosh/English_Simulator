package com.viktoriagavrosh.englishsimulator.ui.features.word

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * Composable to display alertDialog for add and update words
 *
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 * @param wordId unique word identifier
 */
@Composable
fun WordUpdateScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    wordId: Int = 0,
) {
    val viewModel: WordUpdateViewModel = koinViewModel {
        parametersOf(wordId)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WordUpdateScreen(
        englishTextProvider = { uiState.word.questionText },
        russianTextProvider = { uiState.word.answerText },
        themeProvider = { uiState.word.theme },
        isWordValidProvider = { uiState.isWordValid },
        onEnglishTextChange = viewModel::updateEnglishText,
        onRussianTextChange = viewModel::updateRussianText,
        onThemeChange = viewModel::updateTheme,
        onSaveClick = {
            viewModel.saveWord()
            onBackClick()
        },
        onDeleteClick = {
            viewModel.deleteWord()
            onBackClick()
        },
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

/**
 * Composable to display alertDialog for add and update words
 *
 * @param englishTextProvider provides word in English
 * @param russianTextProvider provides word in russian
 * @param themeProvider provides theme of word
 * @param isWordValidProvider if true word is valid and can be saved
 * @param onEnglishTextChange callback that is executed when english text is changed
 * @param onRussianTextChange callback that is executed when russian text is changed
 * @param onThemeChange callback that is executed when theme is changed
 * @param onSaveClick callback that is executed when save button is clicked
 * @param onDeleteClick callback that is executed when delete button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun WordUpdateScreen(
    englishTextProvider: () -> String,
    russianTextProvider: () -> String,
    themeProvider: () -> String,
    isWordValidProvider: () -> Boolean,
    onEnglishTextChange: (String) -> Unit,
    onRussianTextChange: (String) -> Unit,
    onThemeChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = onBackClick,
        confirmButton = {
            UpdateScreenButtons(
                isWordValidProvider = isWordValidProvider,
                onDeleteClick = onDeleteClick,
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier,
        text = {
            UpdateScreenContent(
                englishTextProvider = englishTextProvider,
                russianTextProvider = russianTextProvider,
                themeProvider = themeProvider,
                onEnglishTextChange = onEnglishTextChange,
                onRussianTextChange = onRussianTextChange,
                onThemeChange = onThemeChange,
            )
        }
    )
}

/**
 * Composable to display buttons for AlertDialog
 *
 * @param isWordValidProvider if true word is valid and can be saved
 * @param onSaveClick callback that is executed when save button is clicked
 * @param onDeleteClick callback that is executed when delete button is clicked
 * @param onBackClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun UpdateScreenButtons(
    isWordValidProvider: () -> Boolean,
    onDeleteClick: () -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = stringResource(R.string.delete_word),
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
            )
        }
        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier
                .heightIn(min = dimensionResource(R.dimen.button_min_height))
                .testTag(stringResource(R.string.cancel_button_tag))
        ) {
            Text(text = stringResource(R.string.cancel))
        }
        Button(
            onClick = onSaveClick,
            enabled = isWordValidProvider(),
            modifier = Modifier
                .heightIn(min = dimensionResource(R.dimen.button_min_height))
                .testTag(stringResource(R.string.save_button_tag))
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}

/**
 * Composable to display content for AlertDialog
 *
 * @param englishTextProvider provides word in English
 * @param russianTextProvider provides word in russian
 * @param themeProvider provides theme of word
 * @param onEnglishTextChange callback that is executed when english text is changed
 * @param onRussianTextChange callback that is executed when russian text is changed
 * @param onThemeChange callback that is executed when theme is changed
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun UpdateScreenContent(
    englishTextProvider: () -> String,
    russianTextProvider: () -> String,
    themeProvider: () -> String,
    onEnglishTextChange: (String) -> Unit,
    onRussianTextChange: (String) -> Unit,
    onThemeChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier,
    ) {
        UpdateTextField(
            valueProvider = englishTextProvider,
            onValueChange = onEnglishTextChange,
            labelText = stringResource(R.string.english_text)
        )
        UpdateTextField(
            valueProvider = russianTextProvider,
            onValueChange = onRussianTextChange,
            labelText = stringResource(R.string.russian_text)
        )
        UpdateTextField(
            valueProvider = themeProvider,
            onValueChange = onThemeChange,
            labelText = stringResource(R.string.theme)
        )
    }
}

/**
 * Composable to display TextField for AlertDialog
 *
 * @param valueProvider provides text
 * @param onValueChange callback that is executed when text is changed
 * @param labelText text to be displayed inside the text field container
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
private fun UpdateTextField(
    valueProvider: () -> String,
    onValueChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = valueProvider(),
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(text = labelText)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = { keyboardController?.hide() }
        ),
        modifier = modifier,
    )
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WordUpdateDialogPreview() {
    EnglishSimulatorTheme {
        WordUpdateScreen(
            englishTextProvider = { "En Text" },
            russianTextProvider = { "Ru Text" },
            themeProvider = { "Theme" },
            isWordValidProvider = { true },
            onEnglishTextChange = {},
            onRussianTextChange = {},
            onThemeChange = {},
            onSaveClick = {},
            onDeleteClick = {},
            onBackClick = {},
        )
    }
}
