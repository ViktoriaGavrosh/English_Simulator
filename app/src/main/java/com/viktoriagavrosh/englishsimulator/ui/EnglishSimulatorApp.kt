package com.viktoriagavrosh.englishsimulator.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.viktoriagavrosh.englishsimulator.ui.navigation.AppNavigation

/**
 * Composable entry point of application UI
 *
 * @param windowSize parameter describes screen orientation
 * @param modifier the modifier to be applied to the layout
 */
@Composable
fun EnglishSimulatorApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    AppNavigation(
        isVerticalScreen = windowSize != WindowWidthSizeClass.Expanded,
        modifier = modifier,
    )
}
