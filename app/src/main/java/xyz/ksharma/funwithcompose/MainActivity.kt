package xyz.ksharma.funwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.ksharma.funwithcompose.compositionlocal.LocalSharedTransitionScope
import xyz.ksharma.funwithcompose.navigation.animComposable
import xyz.ksharma.funwithcompose.screen.HomeRoute
import xyz.ksharma.funwithcompose.screen.HomeScreen
import xyz.ksharma.funwithcompose.screen.SharedDetailElementRoute
import xyz.ksharma.funwithcompose.screen.SharedDetailElementScreen
import xyz.ksharma.funwithcompose.screen.SharedElementRoute
import xyz.ksharma.funwithcompose.screen.SharedElementScreen
import xyz.ksharma.funwithcompose.ui.theme.FunWithComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedTransitionLayout {
                val navController = rememberNavController()
                FunWithComposeTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        CompositionLocalProvider(
                            LocalSharedTransitionScope provides this@SharedTransitionLayout,
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = HomeRoute,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {
                                composable<HomeRoute> {
                                    HomeScreen { route ->
                                        navController.navigate(route)
                                    }
                                }
                                animComposable<SharedElementRoute> {
                                    SharedElementScreen(
                                        onHelloWorldClick = {
                                            navController.navigate(SharedDetailElementRoute)
                                        },
                                    )
                                }

                                animComposable<SharedDetailElementRoute> {
                                    SharedDetailElementScreen(
                                        onBackClick = {
                                            navController.popBackStack()
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
