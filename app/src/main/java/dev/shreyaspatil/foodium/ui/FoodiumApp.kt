package dev.shreyaspatil.foodium.ui

import androidx.compose.runtime.Composable
import dev.shreyaspatil.foodium.ui.main.MainScreen
import dev.shreyaspatil.foodium.ui.theme.FoodiumTheme

@Composable
fun FoodiumApp() {
    FoodiumTheme {
        MainScreen()
    }
}