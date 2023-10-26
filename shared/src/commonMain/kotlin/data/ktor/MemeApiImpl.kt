package data.ktor

import data.response.MemeListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json

class MemeApiImpl(engine: HttpClientEngine) : MemeApi {
    private val client = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    override suspend fun getJsonFromApi(): MemeListResponse {
        return client.get {
            memes("get_memes")
        }.body()
    }

    private fun HttpRequestBuilder.memes(path: String) {
        url {
            takeFrom("https://api.imgflip.com/")
            encodedPath = path
        }
    }
}