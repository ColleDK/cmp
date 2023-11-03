
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import domain.Pokemon
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
internal fun PokemonList(
    pokemonList: LazyPagingItems<Pokemon>
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 24.dp)
    ) {
        items(pokemonList.itemCount) { index ->
            val pokemon = pokemonList[index]
            pokemon?.let {
                PokemonListItem(
                    pokemon = it,
                    index = index
                )
            }
        }
    }
}

@Composable
private fun PokemonListItem(
    pokemon: Pokemon,
    index: Int
) {
    var isOpen by rememberSaveable {
        mutableStateOf(false)
    }

    ElevatedCard(
        modifier = Modifier.clickable {
            isOpen = !isOpen
        },
        colors = CardDefaults.cardColors(
            containerColor = pokemon.type?.color ?: Color.Unspecified
        )
    ) {
        ListItemTitle(
            pokemon = pokemon,
            index = index,
            isOpen = isOpen
        )

        AnimatedVisibility(isOpen) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(paddingValues = PaddingValues(all = 8.dp))
            ) {
                Text(
                    text = "Type - ${pokemon.type}",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium
                )
                Images(pokemon = pokemon)
            }
        }
    }
}

@Composable
private fun ListItemTitle(
    pokemon: Pokemon,
    index: Int,
    isOpen: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(paddingValues = PaddingValues(all = 8.dp))
    ) {
        Text(
            text = "$index: ${pokemon.name.capitalize(Locale.current)}",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f),
        )

        Crossfade(targetState = isOpen) { isOpen ->
            if (isOpen) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(degrees = -90f)
                )
            }
        }
    }
}

@Composable
private fun Images(
    pokemon: Pokemon
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        KamelImage(
            resource = asyncPainterResource(data = pokemon.imageFrontUrl ?: ""),
            contentDescription = "Image of ${pokemon.name}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.weight(1f),
            onLoading = {
                LinearProgressIndicator(progress = it)
            }
        )
        KamelImage(
            resource = asyncPainterResource(data = pokemon.shinyImageFrontUrl ?: ""),
            contentDescription = "Image of ${pokemon.name}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.weight(1f),
            onLoading = {
                LinearProgressIndicator(progress = it)
            }
        )
    }
}