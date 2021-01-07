package dev.shreyaspatil.foodium.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import dev.shreyaspatil.foodium.R

private val GoogleSans = fontFamily(
    font(R.font.google_sans)
)

val FoodiumTypography = Typography(
    h6 = TextStyle(
        fontFamily = GoogleSans,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
)