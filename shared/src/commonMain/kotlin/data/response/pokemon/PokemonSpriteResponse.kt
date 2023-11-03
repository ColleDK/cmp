package data.response.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpriteResponse(
    val front_default: String?,
    val front_shiny: String?
)
