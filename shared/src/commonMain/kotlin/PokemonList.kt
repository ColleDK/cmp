
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems
import data.response.pokemon.PokemonResponse

@Composable
internal fun PokemonList(
    pokemonList: LazyPagingItems<PokemonResponse>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList.itemCount) { index ->
            val pokemon = pokemonList[index]
            pokemon?.let {
                PokemonListItem(it)
            }
        }
    }
}

@Composable
private fun PokemonListItem(
    pokemon: PokemonResponse
) {
    ElevatedCard {
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}