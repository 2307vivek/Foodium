package dev.shreyaspatil.foodium.utils

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

fun onBulbClick(context: Context) {
    val mode =
        if ((context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
            Configuration.UI_MODE_NIGHT_NO
        ) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }

    // Change UI Mode
    AppCompatDelegate.setDefaultNightMode(mode)
}