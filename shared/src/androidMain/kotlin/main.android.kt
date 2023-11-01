import androidx.compose.runtime.Composable
import domain.models.MemeViewModel

@Composable fun MainView(viewModel: MemeViewModel) = App(memeViewModel = viewModel)

