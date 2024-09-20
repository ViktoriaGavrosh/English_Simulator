package com.viktoriagavrosh.englishsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.viktoriagavrosh.englishsimulator.ui.EnglishSimulatorApp
import com.viktoriagavrosh.englishsimulator.ui.theme.EnglishSimulatorTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishSimulatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize: WindowSizeClass = calculateWindowSizeClass(activity = this)

                    EnglishSimulatorApp(
                        windowSize = windowSize.widthSizeClass,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}
