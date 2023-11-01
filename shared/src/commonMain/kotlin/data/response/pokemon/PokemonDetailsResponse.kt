package data.response.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsResponse(
    val sprites: PokemonSpriteResponse,
    val types: List<PokemonTypeResponse>
)
