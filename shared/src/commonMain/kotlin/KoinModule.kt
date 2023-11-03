
import data.DatabaseHelper
import data.MemeRepositoryImpl
import data.PokemonPagingSource
import data.ktor.MemeApi
import data.ktor.MemeApiImpl
import data.ktor.pokemon.PokemonApi
import data.ktor.pokemon.PokemonApiImpl
import domain.repository.MemeRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            platformModule,
            coreModule
        )
    }

    val koin = koinApplication.koin

    val doOnStartup = koin.get<() -> Unit>()
    doOnStartup.invoke()

    return koinApplication
}

private val coreModule = module {
    single {
        DatabaseHelper(
            get(),
            Dispatchers.Default
        )
    }

    single<MemeApi> {
        MemeApiImpl(get())
    }

    single<MemeRepository> {
        MemeRepositoryImpl(
            get(),
            get()
        )
    }

    single<PokemonApi> {
        PokemonApiImpl(get())
    }

    single {
        PokemonPagingSource(get())
    }
}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

expect val platformModule: Module