package data.ktor

import data.response.MemeListResponse

interface MemeApi {
    suspend fun getJsonFromApi(): MemeListResponse
}