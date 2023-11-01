package data.response.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeResponse(
    val slot: Int,
    val type: PokemonTypeDataResponse
)
