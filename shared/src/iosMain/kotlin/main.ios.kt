
import androidx.compose.ui.window.ComposeUIViewController
import domain.models.PokemonViewModel

fun MainViewController(viewModel: PokemonViewModel) = ComposeUIViewController { App(viewModel = viewModel) }

