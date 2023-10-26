package data.response

import kotlinx.serialization.Serializable

@Serializable
data class MemeResponse(
    val id: String,
    val name: String,
    val url: String,
    val width: Int,
    val height: Int,
    val box_count: Int
)