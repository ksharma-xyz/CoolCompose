package xyz.ksharma.funwithcompose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClickListener: (Any) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {

        Text(
            text = "Fun With Compose",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )

        examples.forEach {
            Text(
                text = it::class.simpleName ?: "Unknown",
                modifier = Modifier
                    .clickable { onClickListener(it) }
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

val examples = listOf(
    SharedElementRoute,
)
