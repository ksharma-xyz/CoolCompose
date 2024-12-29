package xyz.ksharma.funwithcompose.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.ksharma.funwithcompose.compositionlocal.LocalNavAnimatedVisibilityScope

/**
 * Use this when shared element transitions are required, otherwise keep using [composable] directly.
 */
inline fun <reified T : Any> NavGraphBuilder.animComposable(
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable<T> { backStackEntry ->
        CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this) {
            content(backStackEntry)
        }
    }
}
