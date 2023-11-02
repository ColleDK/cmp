package data.ktor

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import data.response.pokemon.PokemonDetailsResponse
import data.response.pokemon.PokemonListResponse
import data.response.pokemon.mapToDomain
import domain.Pokemon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokemonPagingSource(
    engine: HttpClientEngine
): PagingSource<Int, Pokemon>() {
    companion object {
        const val FIRST_PAGE_INDEX = 0
        const val PAGE_SIZE = 20
        const val INITIAL_LOAD_SIZE = 40
        const val PREFETCH_DISTANCE = 20
    }

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

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: FIRST_PAGE_INDEX
        val httpResponse = client.get("https://pokeapi.co/api/v2/pokemon/") {
            url {
                parameters.append("offset", (PAGE_SIZE * page).toString())
                parameters.append("limit", PAGE_SIZE.toString())
            }
        }
        return when {
            httpResponse.status.isSuccess() -> {
                val pokemon = httpResponse.body<PokemonListResponse>()
                val nextKey = getAdjacentPageKey(
                    totalCount = pokemon.count,
                    requestedLoadSize = params.loadSize,
                    pageKey = page
                )

                val result = pokemon.results.map {
                    val response = client.get(it.url)
                    val details = when(response.status.isSuccess()) {
                        true -> {
                            response.body<PokemonDetailsResponse>()
                        }
                        else -> {
                            null
                        }
                    }

                    Pokemon(
                        name = it.name,
                        imageFrontUrl = details?.sprites?.front_default,
                        shinyImageFrontUrl = details?.sprites?.front_shiny,
                        type = details?.types?.get(0)?.mapToDomain()
                    )
                }

                LoadResult.Page(
                    data = result,
                    nextKey = nextKey,
                    prevKey = null
                )
            }
            else -> {
                LoadResult.Error(Exception("Received a ${httpResponse.status}."))
            }
        }
    }

    private fun getAdjacentPageKey(totalCount: Int, requestedLoadSize: Int, pageKey: Int): Int? {
        val currentCount = INITIAL_LOAD_SIZE + (requestedLoadSize * (pageKey - 1))
        return if (totalCount <= currentCount) null else pageKey + 1
    }
}