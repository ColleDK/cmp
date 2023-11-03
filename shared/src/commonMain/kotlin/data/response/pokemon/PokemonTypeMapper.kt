package data.response.pokemon

import domain.PokemonType

fun PokemonTypeResponse.mapToDomain(): PokemonType? {
    return when (this.type.name) {
        "normal" -> PokemonType.Normal
        "fire" -> PokemonType.Fire
        "water" -> PokemonType.Water
        "grass" -> PokemonType.Grass
        "flying" -> PokemonType.Flying
        "fighting" -> PokemonType.Fighting
        "poison" -> PokemonType.Poison
        "electric" -> PokemonType.Electric
        "ground" -> PokemonType.Ground
        "rock" -> PokemonType.Rock
        "psychic" -> PokemonType.Psychic
        "ice" -> PokemonType.Ice
        "bug" -> PokemonType.Bug
        "ghost" -> PokemonType.Ghost
        "steel" -> PokemonType.Steel
        "dragon" -> PokemonType.Dragon
        "dark" -> PokemonType.Dark
        "fairy" -> PokemonType.Fairy
        else -> null
    }
}