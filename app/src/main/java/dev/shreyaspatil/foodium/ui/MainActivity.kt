package dev.shreyaspatil.foodium.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import dev.shreyaspatil.foodium.R
import dev.shreyaspatil.foodium.ui.theme.FoodiumTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun App() {
        FoodiumTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.app_name)) },
                        actions = {
                            IconButton(onClick = { onBulbClick() }) {
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
    }

    fun onBulbClick() {
        val mode =
            if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
                Configuration.UI_MODE_NIGHT_NO
            ) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            }

        // Change UI Mode
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}