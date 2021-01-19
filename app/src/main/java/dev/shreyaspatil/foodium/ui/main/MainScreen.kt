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

package dev.shreyaspatil.foodium.ui.main

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.shreyaspatil.foodium.R
import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.ui.theme.connected
import dev.shreyaspatil.foodium.ui.theme.notConnected
import dev.shreyaspatil.foodium.utils.NetworkUtils
import dev.shreyaspatil.foodium.utils.onBulbClick
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen() {
    val mainViewModel: MainViewModel = viewModel()
    val context = AmbientContext.current
    val postsState by mainViewModel.postLiveState.observeAsState()
    val networkState by NetworkUtils.getNetworkLiveData(context).observeAsState()

    Scaffold(
        topBar = {
            FoodiumTopAppBar(stringResource(R.string.app_name), context)
        },
        bodyContent = {

            Column {
                NetworkErrorContent(isConnected = networkState!!)

                if (postsState!!.loading) {
                    Text(text = "Loading")
                } else if (postsState!!.isSuccess) {
                    PostList(posts = postsState!!.data!!)
                } else {
                    Text(text = postsState!!.error!!)
                }
            }
        }
    )
}

@Composable
fun PostList(posts: List<Post>) {
    var topPadding: Int
    LazyColumn(content = {
        itemsIndexed(
            items = posts,
            itemContent = { index, post ->
                topPadding = if (index == 0) 4 else 1
                PostItem(
                    post = post,
                    modifier = Modifier.padding(top = topPadding.dp)
                )
            }
        )
    })
}

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: Post
) {
    Surface(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            TitleAndAuthor(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                post = post
            )

            PostImage(
                modifier = Modifier.preferredSize(90.dp),
                imageUrl = post.imageUrl!!
            )
        }
    }
}

@Composable
fun TitleAndAuthor(
    modifier: Modifier = Modifier,
    post: Post
) {
    Column(modifier = modifier) {
        Text(
            text = post.title!!,
            style = MaterialTheme.typography.body1,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Text(
                text = post.author!!,
                style = MaterialTheme.typography.body2,
            )
        }
    }

}

@Composable
fun PostImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    CoilImage(
        modifier = modifier,
        data = imageUrl,
        contentScale = ContentScale.Crop,
        fadeIn = true,
        loading = {
            Image(vectorResource(R.drawable.ic_photo), alpha = 0.45f)
        },
        error = {
            Image(vectorResource(R.drawable.ic_broken_image), alpha = 0.45f)
        }
    )
}

@Composable
fun FoodiumTopAppBar(title: String, context: Context) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { onBulbClick(context) }) {
                Icon(
                    imageVector = vectorResource(id = R.drawable.ic_lightbulb),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun NetworkErrorContent(isConnected: Boolean) {
    AnimatedVisibility(
        visible = !isConnected,
        enter = fadeIn(
            animSpec = tween(
                durationMillis = 1000,
            )
        ),
        exit = fadeOut(
            animSpec = tween(
                durationMillis = 100,
                delayMillis = 1000
            )
        )
    ) {
        val backgroundColor = if (!isConnected) notConnected else connected
        val text = if (isConnected) {
            stringResource(id = R.string.text_connectivity)
        } else {
            stringResource(id = R.string.text_no_connectivity)
        }
        NetworkErrorText(text, backgroundColor)
    }
}

@Composable
fun NetworkErrorText(
    text: String,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}