package domain

data class Pokemon(
    val name: String,
    val imageFrontUrl: String?,
    val shinyImageFrontUrl: String?,
    val type: PokemonType?
)