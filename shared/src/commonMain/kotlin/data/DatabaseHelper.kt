package data

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.TransactionWithoutReturn
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.colledk.cmp.db.ColleDB
import com.colledk.cmp.db.Meme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DatabaseHelper(
    sqlDriver: SqlDriver,
    private val backgroundDispatcher: CoroutineDispatcher
) {
    private val dbRef: ColleDB = ColleDB(sqlDriver)

    fun getAllMemes(): Flow<List<Meme>> = dbRef.memeQueries
        .selectAll()
        .asFlow()
        .mapToList(Dispatchers.Default)
        .flowOn(backgroundDispatcher)

    suspend fun insertMemes(memes: List<Meme>) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            memes.forEach {
                dbRef.memeQueries.insertMeme(
                    name = it.name,
                    url = it.url,
                    width = it.width,
                    height = it.height,
                    boxCount = it.boxCount
                )
            }
        }
    }
}

suspend fun Transacter.transactionWithContext(
    coroutineContext: CoroutineContext,
    noEnclosing: Boolean = false,
    body: TransactionWithoutReturn.() -> Unit
) {
    withContext(coroutineContext) {
        this@transactionWithContext.transaction(noEnclosing) {
            body()
        }
    }
}