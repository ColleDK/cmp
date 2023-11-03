package domain.models

import androidx.paging.PagingData
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.cachedIn
import data.ktor.PokemonPagingSource
import data.ktor.PokemonPagingSource.Companion.INITIAL_LOAD_SIZE
import data.ktor.PokemonPagingSource.Companion.PAGE_SIZE
import data.ktor.PokemonPagingSource.Companion.PREFETCH_DISTANCE
import domain.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonViewModel(
    pagingSource: PokemonPagingSource
): BaseViewModel() {

    val pokemon: Flow<PagingData<Pokemon>> = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE,
            enablePlaceholders = true
        )
    ) {
        pagingSource
    }.flow.cachedIn(scope = scope)
}