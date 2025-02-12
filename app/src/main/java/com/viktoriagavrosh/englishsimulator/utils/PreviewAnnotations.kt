package com.viktoriagavrosh.englishsimulator.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

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

