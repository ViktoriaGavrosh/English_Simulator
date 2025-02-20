package com.viktoriagavrosh.englishsimulator.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.viktoriagavrosh.englishsimulator.ui.screens.menu.model.MenuButtonItem

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class VerticalScreenPreview

@Preview(showBackground = true, widthDp = 1000, name = "Light")
@Preview(
    showBackground = true,
    widthDp = 1000,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class HorizontalScreenPreview

class IsTruePreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(false, true)
}


class ErrorResultPreviewParameterProvider :
    PreviewParameterProvider<RequestResult<List<String>>> {
    override val values = sequenceOf(
        RequestResult.Success(
            List(8) { "Theme $it" }
        ),
        RequestResult.Error()
    )
}

class ButtonItemsPreviewParameterProvider :
    PreviewParameterProvider<List<MenuButtonItem>> {
    override val values = sequenceOf(
        List(8) {
            MenuButtonItem(title = "Button $it")
        },
        List(2) {
            MenuButtonItem(title = "Button $it")
        }
    )
}
