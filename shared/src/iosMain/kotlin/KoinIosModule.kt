import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.colledk.cmp.db.ColleDB
import domain.models.MemeViewModel
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

fun initKoinIos(
    doOnStartup: () -> Unit
): KoinApplication = initKoin(
    module {
        single { doOnStartup }
    }
)

actual val platformModule = module {
    single<SqlDriver> { NativeSqliteDriver(schema = ColleDB.Schema, name = "ColleDB") }
    single { Darwin.create() }
    single { MemeViewModel(get()) }
}

object KotlinDependencies: KoinComponent {
    fun getMemeViewModel() = getKoin().get<MemeViewModel>()
}