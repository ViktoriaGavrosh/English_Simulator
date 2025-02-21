package com.viktoriagavrosh.englishsimulator.ui.screens.menu.elements

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display selection menu
 *
 * @param options list of items to select
 * @param selectedOptionProvider provides selected item
 * @param onValueChange callback that is executed when item is selected
 * @param modifier the modifier to be applied to this layout node
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SelectionDropdownMenu(
    options: List<String>,
    selectedOptionProvider: () -> String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textFieldColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5F)

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = selectedOptionProvider(),
            onValueChange = onValueChange,
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = textFieldColor,
                unfocusedContainerColor = textFieldColor,
            )
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item)
                    },
                    onClick = {
                        isExpanded = false
                        onValueChange(item)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SelectionDropdownMenuPreview() {
    val options = List(3) { "Option $it of dropdown menu" }
    EnglishSimulatorTheme {
        SelectionDropdownMenu(
            options = options,
            selectedOptionProvider = { options[0] },
            onValueChange = {},
        )
    }
}
