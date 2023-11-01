
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.colledk.cmp.db.Meme
import domain.models.MemeViewModel

@Composable
fun App(
    memeViewModel: MemeViewModel
) {
    MaterialTheme {
        val memes by memeViewModel.memes.collectAsState()
        MemeList(memes = memes)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MemeList(
    memes: List<Meme>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(memes) {
            ListItem {
                Text("Received meme ${it.url}")
            }
        }
    }
}
