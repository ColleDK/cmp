package domain

data class Pokemon(
    val name: String,
    val imageFrontUrl: String?,
    val shinyImageFrontUrl: String?,
    val type: Type?
)

enum class Type(val typeName: String) {
    FAIRY("fairy"),
    WATER("water"),
    FIRE("fire"),
    DRAGON("dragon"),
    GRASS("grass")
}