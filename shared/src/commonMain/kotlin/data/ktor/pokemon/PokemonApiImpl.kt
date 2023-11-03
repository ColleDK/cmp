package data.ktor.pokemon

import data.response.pokemon.PokemonDetailsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokemonApiImpl(
    engine: HttpClientEngine
): PokemonApi {
    private val client = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    override suspend fun getPokemonList(offset: String, limit: String): HttpResponse {
        return client.get("https://pokeapi.co/api/v2/pokemon/") {
            url {
                parameters.append("offset", offset)
                parameters.append("limit", limit)
            }
        }
    }

    override suspend fun getPokemonDetails(url: String): PokemonDetailsResponse? {
        val result = client.get(url)
        return when(result.status.isSuccess()) {
            true -> {
                result.body()
            }
            false -> {
                null
            }
        }
    }
}