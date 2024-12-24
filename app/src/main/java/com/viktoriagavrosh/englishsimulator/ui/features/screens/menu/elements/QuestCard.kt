package com.viktoriagavrosh.englishsimulator.ui.features.screens.menu.elements

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

/**
 * Composable to display card for menu
 *
 * @param onClick callback that is executed when card is clicked
 * @param text the text to be displayed
 * @param modifier the modifier to be applied to this layout node
 */
@Composable
internal fun QuestCard(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier,
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_extra_large))
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun QuestCardPreview() {
    EnglishSimulatorTheme {
        QuestCard(
            onClick = {},
            text = "Text",
        )
    }
}
