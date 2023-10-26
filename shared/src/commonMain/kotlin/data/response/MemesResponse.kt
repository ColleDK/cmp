package data.response

import kotlinx.serialization.Serializable

@Serializable
data class MemesResponse(
    val memes: List<MemeResponse>
)