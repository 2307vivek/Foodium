/*
 * MIT License
 *
 * Copyright (c) 2021 Vivek Singh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.shreyaspatil.foodium.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import dev.shreyaspatil.foodium.ui.details.PostDetailScreen
import dev.shreyaspatil.foodium.ui.main.MainScreen
import dev.shreyaspatil.foodium.ui.main.MainViewModel
import dev.shreyaspatil.foodium.ui.navigation.Screen
import dev.shreyaspatil.foodium.ui.theme.FoodiumTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun FoodiumApp(mainViewModel: MainViewModel) {
    FoodiumTheme {
        FoodiumNavigation(mainViewModel)
    }
}

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun FoodiumNavigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
    ) {
        composable(Screen.Main.route) {
            MainScreen(mainViewModel,navController)
        }

        composable(
            route = Screen.PostDetail.route,
            arguments = listOf(navArgument(Screen.PostDetail.postId) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            PostDetailScreen(
                postId = navBackStackEntry.arguments?.getInt(Screen.PostDetail.postId),
                mainViewModel = mainViewModel,
                navController = navController
            )
        }
    }
}