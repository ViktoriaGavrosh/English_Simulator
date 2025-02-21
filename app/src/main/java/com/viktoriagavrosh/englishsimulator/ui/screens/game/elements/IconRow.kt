package com.viktoriagavrosh.englishsimulator.ui.screens.game.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.viktoriagavrosh.englishsimulator.R
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme
import com.viktoriagavrosh.englishsimulator.utils.VerticalScreenPreview

/**
 * Composable to display back button on top of screen
 *
 * @param iconId resources object to query the icon file from
 * @param contentDescription describe icon`s action
 * @param onIconClick callback that is executed when back button is clicked
 * @param modifier the modifier to be applied to this layout node
 * @param isLeft
 */
@Composable
internal fun IconRow(
    @DrawableRes iconId: Int,
    contentDescription: String,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLeft: Boolean = true,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = if (isLeft) {
            Arrangement.Start
        } else {
            Arrangement.End
        },
    ) {
        OutlinedIconButton(
            onClick = onIconClick,
            modifier = Modifier
                .size(dimensionResource(R.dimen.back_button_size))
                .padding(dimensionResource(R.dimen.padding_double_small)),
            border = IconButtonDefaults.outlinedIconButtonBorder(false)
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_size))
                    .padding(dimensionResource(R.dimen.padding_small)),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@VerticalScreenPreview
@Composable
private fun RightIconRowPreview() {
    EnglishSimulatorTheme {
        IconRow(
            iconId = R.drawable.ic_back,
            contentDescription = "",
            onIconClick = {}
        )
    }
}

@VerticalScreenPreview
@Composable
private fun LeftIconRowPreview() {
    EnglishSimulatorTheme {
        IconRow(
            iconId = R.drawable.ic_edit,
            contentDescription = "",
            onIconClick = {},
            isLeft = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
