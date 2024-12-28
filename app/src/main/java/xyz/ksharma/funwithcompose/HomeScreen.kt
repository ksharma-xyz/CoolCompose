package xyz.ksharma.funwithcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {

       /* examples.forEach {
            Text(
                text = it.serializer().descriptor.serialName,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }*/
    }
}

val examples = listOf(
    SharedElementRoute
)
