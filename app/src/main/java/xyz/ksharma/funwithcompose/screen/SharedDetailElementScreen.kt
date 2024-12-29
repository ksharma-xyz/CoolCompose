package xyz.ksharma.funwithcompose.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import xyz.ksharma.funwithcompose.compositionlocal.LocalNavAnimatedVisibilityScope
import xyz.ksharma.funwithcompose.compositionlocal.LocalSharedTransitionScope

@Serializable
data object SharedDetailElementRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedDetailElementScreen(
    onBackClick: () -> Unit,
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No AnimatedVisibility found")

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        delay(200)
        keyboard?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        IconButton(
            onClick = {
                keyboard?.hide()
                onBackClick()
            },
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }

        with(sharedTransitionScope) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 48.dp)
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "HelloKey"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds(),
                    )
                    .focusRequester(focusRequester)
                    .clip(RoundedCornerShape(50))
                    .background(color = Color.Green)
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center,
            ) {
                TextField(
                    value = "Hello World",
                    onValueChange = { /*TODO*/ },
                )
            }
        }
    }
}
