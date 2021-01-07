package dev.shreyaspatil.foodium.ui.main

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.viewinterop.viewModel
import dev.shreyaspatil.foodium.R

@Composable
fun MainScreen() {
    val context = AmbientContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = { onBulbClick(context) }) {
                        Icon(
                            imageVector = vectorResource(id = R.drawable.ic_lightbulb),
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            )
        },
        bodyContent = {
            Text(text = "Hello Compose")
        }
    )
}

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