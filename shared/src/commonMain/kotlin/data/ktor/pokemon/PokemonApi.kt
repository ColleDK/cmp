package data.ktor.pokemon

import data.response.pokemon.PokemonDetailsResponse
import io.ktor.client.statement.HttpResponse

interface PokemonApi {
    suspend fun getPokemonList(offset: String, limit: String): HttpResponse
    suspend fun getPokemonDetails(url: String): PokemonDetailsResponse?
}