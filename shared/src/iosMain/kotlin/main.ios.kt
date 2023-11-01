import androidx.compose.ui.window.ComposeUIViewController
import domain.models.MemeViewModel

fun MainViewController(viewModel: MemeViewModel) = ComposeUIViewController { App(memeViewModel = viewModel) }

