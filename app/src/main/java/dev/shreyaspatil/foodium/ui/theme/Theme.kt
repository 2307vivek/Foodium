package dev.shreyaspatil.foodium.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightFoodiumColors = lightColors(
    primary = Color.White,
    primaryVariant = Color.White,
    secondary = colorAccent,
    background = windowBackground,
    surface = Color.White,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = notConnected
)

private val DarkFoodiumColors = lightColors(
    primary = colorPrimaryDark,
    primaryVariant = colorPrimaryDark,
    secondary = colorAccent,
    background = windowBackgroundDark,
    surface = colorPrimaryDark,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = notConnected
)

@Composable
fun FoodiumTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDark) DarkFoodiumColors else LightFoodiumColors,
        typography = FoodiumTypography,
        content = content
    )
}