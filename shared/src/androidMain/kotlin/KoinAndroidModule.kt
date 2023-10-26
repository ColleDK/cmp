import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.colledk.cmp.db.ColleDB
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = ColleDB.Schema,
            context = get(),
            name = "ColleDB"
        )
    }

    single {
        OkHttp.create()
    }
}