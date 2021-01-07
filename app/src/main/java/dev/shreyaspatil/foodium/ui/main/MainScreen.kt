package dev.shreyaspatil.foodium.ui.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import dev.shreyaspatil.foodium.R
import dev.shreyaspatil.foodium.utils.onBulbClick

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