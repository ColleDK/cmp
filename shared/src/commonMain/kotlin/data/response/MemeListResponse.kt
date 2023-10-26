package data.response

import kotlinx.serialization.Serializable

@Serializable
data class MemeListResponse(
    val success: Boolean,
    val data: MemesResponse
)
