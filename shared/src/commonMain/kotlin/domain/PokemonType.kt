package domain

import androidx.compose.ui.graphics.Color

sealed class PokemonType(val color: Color) {
    data object Normal: PokemonType(color = Color(0xFFA8A77A))
    data object Fire: PokemonType(color = Color(0xFFEE8130))
    data object Water: PokemonType(color = Color(0xFF6390F0))
    data object Grass: PokemonType(color = Color(0xFF7AC74C))
    data object Flying: PokemonType(color = Color(0xFFA98FF3))
    data object Fighting: PokemonType(color = Color(0xFFC22E28))
    data object Poison: PokemonType(color = Color(0xFFA33EA1))
    data object Electric: PokemonType(color = Color(0xFFF7D02C))
    data object Ground: PokemonType(color = Color(0xFFE2BF65))
    data object Rock: PokemonType(color = Color(0xFFB6A136))
    data object Psychic: PokemonType(color = Color(0xFFF95587))
    data object Ice: PokemonType(color = Color(0xFF96D9D6))
    data object Bug: PokemonType(color = Color(0xFFA6B91A))
    data object Ghost: PokemonType(color = Color(0xFF735797))
    data object Steel: PokemonType(color = Color(0xFFB7B7CE))
    data object Dragon: PokemonType(color = Color(0xFF6F35FC))
    data object Dark: PokemonType(color = Color(0xFF705746))
    data object Fairy: PokemonType(color = Color(0xFFD685AD))
}
