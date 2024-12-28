package xyz.ksharma.funwithcompose.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data object SharedElementRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Shared Element Transitions",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        var showDetails by rememberSaveable {
            mutableStateOf(false)
        }
        SharedTransitionLayout {
            AnimatedContent(
                showDetails,
                label = "basic_transition"
            ) { targetState ->
                if (!targetState) {
                    DetailContents(
                        onShowDetails = {
                            showDetails = true
                        },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                } else {
                    MainContent(
                        onBack = {
                            showDetails = false
                        },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainContent(
    onBack: () -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
            .background(Color.Yellow, RoundedCornerShape(8.dp))
            .clickable {
                onBack()
            }
            .padding(8.dp)
    ) {
        with(sharedTransitionScope) {
            Column(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "myContentKey"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            ) {

                Text(
                    text = "Heading",
                    color = Color.Black,
                    modifier = Modifier.sharedBounds(
                        rememberSharedContentState(key = "title"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Red, shape = CircleShape)
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(key = "circle"),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ),

                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "1",
                            color = Color.Black
                        )
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailContents(
    onShowDetails: () -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable {
                onShowDetails()
            }
            .padding(8.dp)
    ) {
        with(sharedTransitionScope) {
            Column(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "myContentKey"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
            ) {

                Text(
                    text = "Heading",
                    color = Color.Black,
                    modifier = Modifier.sharedBounds(
                        rememberSharedContentState(key = "title"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                )

                Text(
                    text = "Details",
                    color = Color.Black
                )

                Text(
                    text = "Some more content here",
                    color = Color.Black
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(
                        text = "Platform 1",
                        color = Color.Black,
                        modifier = Modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "circle"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                    )
                }
            }
        }
    }
}
